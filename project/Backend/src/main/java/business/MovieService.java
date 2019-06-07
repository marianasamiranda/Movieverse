package business;

import data.ElasticSearch;
import data.RedisCache;
import data.daos.*;
import data.entities.*;
import data.entities.Genre;
import data.entities.Movie;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private ElasticSearch elasticSearch;

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
    private TheaterDAO theaterDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private MediaDAO mediaDAO;

    @Autowired
    private Util util;

    @Autowired
    private FeedDAO feedDAO;

    @Autowired
    private RedisCache redisCache;


    public MovieService() {}

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

        var companies = new ArrayList<Map<String, Object>>();
        for(Object c : m.getCompanies()) {
            Company company = (Company) c;
            var h = new HashMap<String, Object>();
            h.put("id", company.getId());
            h.put("name", company.getName());
            companies.add(h);
        }
        result.put("companies", companies);

        var groupedMedia = mediaDAO.getMovieMedia(id);

        if(groupedMedia.get('b')!=null)
            result.put("backdrops", ((List<String>) groupedMedia.get('b')).stream().limit(5).collect(Collectors.toList()));

        if(groupedMedia.get('v')!=null)
            result.put("videos", ((List<String>) groupedMedia.get('v')).stream().limit(5).collect(Collectors.toList()));

        if(groupedMedia.get('p')!=null)
            result.put("posters", ((List<String>) groupedMedia.get('p')).stream().limit(5).collect(Collectors.toList()));

        return result;
    }

    public Map<String, Object> getShort(Integer id) throws Exception {
        Movie m = movieDAO.loadEntityEager("tmdb=" + id);

        Map<String, Object> result = new HashMap<>();
        result.put("name", m.getName());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(m.getRelease());
        int year = calendar.get(Calendar.YEAR);
        result.put("year", year);

        return result;
    }

    /*public boolean addComment(Map<String, String> comment) throws IOException {
        if (false) {
            throw new IOException();
        }
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
                    Feed f = null;
                    try {
                        f = feedDAO.loadEntity("idContent=" + userMovie.getMovie().getORMID()+ " and type=2");
                    }
                    catch(Exception e) { }
                    if(f==null) {
                        f = new Feed();
                        f.setType(2);
                        f.setUser(user);
                        f.setIdContent(userMovie.getMovie().getORMID());
                        feedDAO.merge(f);
                    }
                }
                if(updates.containsKey("rating")) {
                    int rating = (int) updates.get("rating");
                    userMovie.setRating(rating);
                }
                userMovie.setStatus(true);
                userMovie.setDateWatched(dateWatched);

                userMovieDAO.merge(userMovie);
                userMovieDAO.flush();
                Feed f = null;
                try {
                    f = feedDAO.loadEntity("idContent=" + userMovie.getMovie().getORMID()+ " and (type=1 or type=0)");
                }
                catch(Exception e) { }
                if(f!=null && f.getType() == 1) {
                    feedDAO.removeEntity("muserid=" + user.getId() + "and idcontent=" + movieId + " and type=1");
                    f.setType(0);
                    feedDAO.merge(f);
                    feedDAO.flush();
                }
                else if(f==null) {
                    f = new Feed();
                    if(!updates.containsKey("rating")) {
                        f.setType(1);
                    }
                    else {
                        f.setType(0);
                    }
                    f.setUser(user);
                    f.setIdContent(userMovie.getMovie().getORMID());
                    feedDAO.merge(f);
                    feedDAO.flush();
                }
            }
            else {
                if(updates.containsKey("addedToWatchlist")) {
                    userMovie.setStatus(false);
                    userMovie.setDateWatched(null);
                    userMovie.setFavourite(false);
                    userMovie.setDateFavourite(null);
                    userMovie.setRating(null);

                    userMovieDAO.merge(userMovie);
                    userMovieDAO.flush();
                    feedDAO.removeEntity("muserid=" + user.getId() + "and idcontent=" + movieId);
                }
                else {
                    userMovieDAO.removeEntity("muserid=" + user.getId() + " and movieid=" + movieId);
                    feedDAO.removeEntity("muserid=" + user.getId() + "and idcontent=" + movieId);
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

                Feed f = null;
                try {
                    f = feedDAO.loadEntity("idContent=" + userMovie.getMovie().getORMID()+ " and type=2");
                }
                catch(Exception e) { }
                if(f==null) {
                    f = new Feed();
                    f.setType(2);
                    f.setUser(user);
                    f.setIdContent(userMovie.getMovie().getORMID());
                    feedDAO.merge(f);
                }
            }
            else {
                userMovie.setFavourite(false);
                userMovie.setDateFavourite(null);
                userMovie.setRating(null);

                userMovieDAO.merge(userMovie);
                userMovieDAO.flush();
                feedDAO.removeEntity("muserid=" + user.getId() + "and idcontent=" + movieId + " and type=2");

            }
        }
        else if(updates.containsKey("rating")) {
            Integer rating = (Integer) updates.get("rating");
            userMovie.setRating(rating);
            userMovieDAO.merge(userMovie);
            userMovieDAO.flush();
            if(rating!=null && rating == 0) {
                Feed f = null;
                try {
                    f = feedDAO.loadEntity("idContent=" + userMovie.getMovie().getORMID()+ " and type=0");
                }
                catch(Exception e) { }
                if(f!=null) {
                    feedDAO.removeEntity("muserid=" + user.getId() + "and idcontent=" + movieId + " and type=0");
                    f = new Feed();
                    f.setType(1);
                    f.setUser(user);
                    f.setIdContent(userMovie.getMovie().getORMID());
                    feedDAO.merge(f);
                    feedDAO.flush();
                }
            }
            else if(rating!=null && rating != 0) {
                Feed f = null;
                try {
                    f = feedDAO.loadEntity("idContent=" + userMovie.getMovie().getORMID()+ " and type=1");
                }
                catch(Exception e) { }
                if(f!=null) {
                    feedDAO.removeEntity("muserid=" + user.getId() + "and idcontent=" + movieId + " and type=1");
                    f = new Feed();
                    f.setType(0);
                    f.setUser(user);
                    f.setIdContent(userMovie.getMovie().getORMID());
                    feedDAO.merge(f);
                    feedDAO.flush();
                }
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
        var response = elasticSearch.search(search);

        var result = new ArrayList<>();
        for (var r: response.getHits()) {
            var m = r.getSourceAsMap();
            m.put("id", r.getId());
            result.add(m);
        }
        return result;
    }


    public Object showtimes(int theater) {
        String cachedShowtimes = redisCache.get("showtimes_" + theater);

        if (cachedShowtimes != null)
            return cachedShowtimes;

        List showtimes = showtimeDAO.getShowtimes(theater);

        String json = util.toJson(showtimes);
        redisCache.set("showtimes_" + theater, json);
        redisCache.set("showtimes_" + theater + "_date", Long.toString(util.unixTimeSeconds() + 3600)); //1 hour

        return showtimes;
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


    public Object movieSearchPage() {
        String cachedInfo = redisCache.get("movieSearchPageInfo");

        if (cachedInfo != null)
            return cachedInfo;

        Map m = new HashMap();
        m.put("latest", movieDAO.getLatestMovies(0, 30));
        m.put("popular", movieDAO.getPopularMovies(0, 30));
        m.put("upcoming", movieDAO.getUpcomingMovies(0, 30));

        String json = util.toJson(m);
        redisCache.set("movieSearchPageInfo", json);
        redisCache.set("movieSearchPageInfo_date", Long.toString(util.unixTimeSeconds() + 3600)); //1 hour

        return m;
    }


    public List<Integer> theatersIds() {
        return theaterDAO.findAll()
                         .stream()
                         .map(Theater::getId)
                         .collect(Collectors.toList());
    }

    public List randomUpcomingMovies(int limit) {
        return movieDAO.getRandomUpcomingMovies(limit);
    }
}
