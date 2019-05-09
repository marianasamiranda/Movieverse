package business;

import data.daos.GenreDAO;
import data.daos.MUserDAO;
import data.daos.MovieDAO;
import data.daos.UserMovieDAO;
import data.entities.*;
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

    public MovieManager() {}

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

        return result;
    }

    public boolean addComment(Map<String, String> comment) throws IOException {
        if (false) {
            throw new IOException();
        }
        // TODO: quando tiver DAOs
        return true;
    }

    public boolean markWatched(String token, Integer movieId) throws IOException {

        var user = mUserDAO.loadEntityInitalize("token='" + token + "'"  , "id");
        var movie = movieDAO.loadEntity("tmdb=" + movieId);
        var userMovie = new UserMovie();
        var today = new Date();
        today.setTime(0);
        userMovie.setDateWatched(today);

        userMovie.setmUser(user);
        userMovie.setStatus(true);
        userMovie.setMovie(movie);

        userMovieDAO.persist(userMovie);

        return true;
    }

    public boolean markUnwatched(String token, Integer movieId) throws IOException {
        var user = mUserDAO.loadEntityInitalize("token='" + token + "'"  , "id");
        var userMovie = userMovieDAO.loadEntity("muserid=" + user.getId() + " and movieid=" + movieId);
        userMovieDAO.remove(userMovie);
        return true;
    }

    public boolean rateMovie(String token, Integer movieId, Integer rating) throws IOException {
        var user = mUserDAO.loadEntityInitalize("token='" + token + "'"  , "id");
        var movie = movieDAO.loadEntity("tmdb=" + movieId);
        var userMovie = userMovieDAO.loadEntity("muserid=" + user.getId() + " and movieid=" + movieId);

        userMovie.setRating(rating);

        userMovieDAO.merge(userMovie);

        return true;
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
}
