package business;

import data.daos.*;
import data.entities.*;
import data.daos.impl.ShowtimeDAOImpl;
import data.entities.Genre;
import data.entities.Movie;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieManager {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private UserMovieDAO userMovieDAO;

    @Autowired
    private MUserDAO mUserDAO;

    @Autowired
    private GenreDAO genreDAO;

    @Autowired
    private ShowtimeDAO showtimeDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private MediaDAO mediaDAO;

    public MovieManager() {}

    public MUser getUserByToken(String token) {
        try {
            return mUserDAO.loadEntity("token='" + token + "'");
        }
        catch (Exception e) {
            return null;
        }
    }

    public Map<String, Object> get(Integer id) throws Exception {
        Movie m = movieDAO.loadEntityEager("tmdb=" + id);
        double rating = 0;

        try {
            rating = (double) m.getRatingSum()/m.getRatingCount();
            rating = Math.round(rating * 10) / 10.0;
        }
        catch (Exception e) {

        }

        Integer auxI = 0;
        String auxS = null;

        Map<String, Object> result = new HashMap<>();
        result.put("tmdb", m.getTmdb());
        result.put("name", m.getName());
        result.put("poster", m.getPoster());
        result.put("backdrop", m.getBackdrop());
        result.put("plot", m.getPlot());
        result.put("imdb", m.getImdb());
        result.put("release", m.getRelease());
        result.put("runtime", m.getRuntime());
        result.put("rating", rating);
        if ((auxI = m.getBudget()) != null) {
            result.put("budget", auxI);
        }
        if ((auxS = m.getLanguage()) != null) {
            result.put("language", auxS);
        }
        if ((auxS = m.getTagline()) != null) {
            result.put("tagline", auxS);
        }
        var genres = new ArrayList<String>();
        for(Object g : m.getGenres()) {
            Genre genre = (Genre) g;
            genres.add(genre.getName());
        }
        result.put("genres", genres);

        var companies = new ArrayList<String>();
        for(Object c : m.getCompanies()) {
            Company company = (Company) c;
            companies.add(company.getName());
        }
        result.put("companies", companies);

        result.put("backdrops", mediaDAO.getMovieMedia(id, 'b', 5));
        result.put("videos", mediaDAO.getMovieMedia(id, 'v', 5));
        result.put("posters", mediaDAO.getMovieMedia(id, 'p', 5));

        return result;
    }

    /*public boolean addComment(Map<String, String> comment) throws IOException {
        if (false) {
            throw new IOException();
        }
        // TODO: quando tiver DAOs
        return true;
    }*/

    public HashMap<Object, Object> getMovieMeInfo(String token, Integer movieId) throws IOException {

        var user = getUserByToken(token);
        var userMovie = new UserMovie();

        try {
            userMovie = userMovieDAO.loadEntity("muserid=" + user.getId() + " and movieid=" + movieId);
        }
        catch(Exception e) {
            throw new IOException();
        }

        var result = new HashMap<>();

        if(userMovie.getStatus()) {
            result.put("watched", true);
            result.put("dateWatched", userMovie.getDateWatched());
            Integer r;
            if((r = userMovie.getRating()) != null) {
                result.put("isRated", true);
                result.put("rating", r);
            }
            if(userMovie.getFavourite()) {
                result.put("favourite", true);
                result.put("dateFavourited", userMovie.getDateFavourite());
            }
        }
        else {
            result.put("watchlist", !userMovie.getStatus());
        }

        return result;
    }

    public boolean patchMovieMeInfo(String token, Integer movieId, Map<String, Object> updates) throws IOException {

        var user = getUserByToken(token);
        var userMovie = new UserMovie();

        try {
            userMovie = userMovieDAO.loadEntity("muserid=" + user.getId() + " and movieid=" + movieId);
        }
        catch(Exception e) {
            userMovie.setMovie(movieDAO.findById(movieId));
            userMovie.setmUser(user);
        }

        if(updates.containsKey("watched")) {
            boolean watched = (boolean) updates.get("watched");

            if(watched) {
                var dateWatched = new Date();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                    dateWatched = sdf.parse((String) updates.get("dateWatched"));
                }
                catch(ParseException e) {
                    e.printStackTrace();
                }
                if(updates.containsKey("favourited")) {
                    setFavourite(updates, userMovie);
                }
                userMovie.setStatus(true);
                userMovie.setDateWatched(dateWatched);

                userMovieDAO.merge(userMovie);
                userMovieDAO.flush();
            }
            else {
                if(updates.containsKey("addedToWatchlist")) {
                    userMovie.setStatus(false);
                    userMovie.setDateWatched(null);
                    userMovie.setFavourite(false);
                    userMovie.setDateFavourite(null);

                    userMovieDAO.merge(userMovie);
                    userMovieDAO.flush();
                }
                else {
                    userMovieDAO.removeEntity("muserid=" + user.getId() + " and movieid=" + movieId);
                }
            }
        }
        else if(updates.containsKey("addedToWatchlist")) {
            boolean addedToWatchlist = (boolean) updates.get("addedToWatchlist");

            if(addedToWatchlist) {
                userMovie.setStatus(false);

                userMovieDAO.merge(userMovie);
                userMovieDAO.flush();
            }
            else {
                userMovieDAO.removeEntity("muserid=" + user.getId() + " and movieid=" + movieId);
            }
        }
        else if(updates.containsKey("favourited")) {
            boolean favourite = (boolean) updates.get("favourited");
            if (favourite) {
                setFavourite(updates, userMovie);

                userMovieDAO.merge(userMovie);
                userMovieDAO.flush();
            }
            else {
                userMovie.setFavourite(false);
                userMovie.setDateFavourite(null);

                userMovieDAO.merge(userMovie);
                userMovieDAO.flush();
            }
        }

        return true;
    }

    private void setFavourite(Map<String, Object> updates, UserMovie userMovie) {
        var dateFavourited = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            dateFavourited = sdf.parse((String) updates.get("dateFavourited"));
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
        userMovie.setFavourite(true);
        userMovie.setDateFavourite(dateFavourited);
    }


    public List search(String title, String sort, String genre) throws IOException {
        if (title.equals("") && sort == null && genre == null)
            return null;

        var search = new SearchRequest("movieverse_movies");
        var builder = new SearchSourceBuilder();

        if (title.equals("") && genre == null)
            builder.query(QueryBuilders.matchAllQuery());

        else {
            var boolQuery = QueryBuilders.boolQuery();
            for (var t : title.split("\\s"))
                boolQuery.should(QueryBuilders.fuzzyQuery("name", t));
            if (genre != null) {
                for (var g: genre.split(","))
                    boolQuery.must(QueryBuilders.matchQuery("genre", g));
            }
            boolQuery.should(QueryBuilders.existsQuery("poster"));
            builder.query(boolQuery);
        }

        if (sort != null) {
            if (sort.equals("dateAsc"))
                builder.sort("release", SortOrder.ASC);
            else if (sort.equals("dateDesc"))
                builder.sort("release", SortOrder.DESC);
            else
                builder.sort("rating", SortOrder.DESC);
        }

        builder.size(30);
        if (genre == null || genre.split(",").length != 1)
            builder.minScore(1.001f);

        search.source(builder);
        var response = client.search(search, RequestOptions.DEFAULT);

        var result = new ArrayList<>();
        for (var r: response.getHits()) {
            var m = r.getSourceAsMap();
            m.put("id", r.getId());
            result.add(m);
        }
        return result;
    }


    public List showtimes(int theater) {
        return showtimeDAO.getShowtimes(theater);
    }

    public int estimatedCount() {
        return movieDAO.estimatedSize();
    }

    public int estimatedNumberOfComments() {
        return commentDAO.estimatedSize();
    }

    public List latestMovies(int begin, int limit) {
        return movieDAO.getLatestMovies(begin, limit);
    }

    public Map movieSearchPage() {
        Map m = new HashMap();
        m.put("latest", movieDAO.getLatestMovies(0, 30));
        m.put("popular", movieDAO.getPopularMovies(0, 30));
        m.put("upcoming", movieDAO.getUpcomingMovies(0, 30));
        return m;
    }

    public List randomUpcomingMovies(int limit) {
        return movieDAO.getRandomUpcomingMovies(limit);
    }
}
