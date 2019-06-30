package business.impl;

import business.MovieService;
import business.UserService;
import business.Util;
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
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

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
    private AchievementDAO achievementDAO;

    @Autowired
    private MediaDAO mediaDAO;

    @Autowired
    private Util util;

    @Autowired
    private FeedDAO feedDAO;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieMemberDAO movieMemberDAO;


    @Override
    @Transactional(readOnly=true)
    public Map<String, Object> get(Integer id) throws Exception {
        Movie m = movieDAO.loadEntityEager(id);

        var rating = 0.0;

        try {
            rating = (double) m.getRatingSum()/m.getRatingCount();
            rating = Math.round(rating * 10) / 10.0;
        }
        catch (Exception e) {
            rating = 0.0;
        }

        Integer auxI;
        String auxS;
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
        result.put("isShowing", showtimeDAO.isShowing(id));
        result.put("watchCount", m.getWatchCount());
        result.put("favouriteCount", m.getFavouriteCount());

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

        if(groupedMedia.get('b') != null)
            result.put("backdrops", ((List<String>) groupedMedia.get('b'))
                    .stream()
                    .limit(5)
                    .collect(Collectors.toList()));

        if(groupedMedia.get('v') != null)
            result.put("videos", ((List<String>) groupedMedia.get('v'))
                    .stream()
                    .limit(5)
                    .collect(Collectors.toList()));

        if(groupedMedia.get('p') != null)
            result.put("posters", ((List<String>) groupedMedia.get('p'))
                    .stream()
                    .limit(5)
                    .collect(Collectors.toList()));

        Map moviemembers = movieMemberDAO.listMainMovieMembers(id,4);
        result.put("actors", moviemembers.get(1) );
        result.put("crew", moviemembers.get(0));

        return result;
    }


    @Override
    public Map<String, Object> getShort(Integer id) throws Exception {
        Movie m = movieDAO.findById(id);

        Map<String, Object> result = new HashMap<>();
        result.put("name", m.getName());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(m.getRelease());
        int year = calendar.get(Calendar.YEAR);
        result.put("year", year);

        return result;
    }


    @Override
    public HashMap<Object, Object> getMovieMeInfo(String token, Integer movieId) throws Exception {

        Integer id = userService.getUserIdByToken(token);
        var userMovie = new UserMovie();


        userMovie = userMovieDAO.getUserMovie(id, movieId);

        if (userMovie == null){
              if(id != null) {
                var result = new HashMap<>();
                result.put("watched", false);
                return result;
            }
            else throw new IOException();
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



    @Override
    @Retryable(value = {StaleStateException.class}, maxAttempts = 5)
    @Transactional
    public boolean patchMovieMeInfo(String token, Integer movieId, Map<String, Object> updates) throws Exception {
        var user = mUserDAO.getSimpleUserByToken(token);
        var movie = movieDAO.findById(movieId);

        var userMovie = userMovieDAO.getUserMovie(user.getId(), movieId);

        if (userMovie == null) {
            userMovie = new UserMovie();
            userMovie.setMovie(movie);
            userMovie.setmUser(user);
            userMovieDAO.persist(userMovie);
        }

        boolean insertUserMovie = true;

        try {
            if (updates.containsKey("watched")) {
                var watched = (boolean) updates.get("watched");
                var feedEntryType = 1;

                if (watched) {
                    // If movie was marked as watched
                    var dateWatched = new Date();

                    if (updates.containsKey("favourited")) {
                        // If movie was also marked as favourite
                        addMovieFavourite(userMovie, user, movie);
                    }
                    if (updates.containsKey("rating") && userMovie.getRating() == null) {
                        // If movie was rated

                        feedEntryType = 0;

                        int rating = (int) updates.get("rating");
                        userMovie.setRating(rating);
                        addMovieRating(user, movie, rating);


                    }
                    addMovieWatched(userMovie, user, movie);
                    addFeedEntry(feedEntryType, user, movie.getORMID(), dateWatched);

                } else {
                    // If movie was marked as unwatched
                    removeMovieWatchedDependencies(userMovie, user, movie);

                    if (updates.containsKey("addedToWatchlist")) {
                        // If movie was also added to watchlist
                        clearUserMovie(userMovie);

                    } else {
                        // If movie was only marked as unwatched
                        userMovieDAO.removeMovieFromUser(user.getId(), movie.getORMID());
                        insertUserMovie = false;
                    }
                }
            } else if (updates.containsKey("addedToWatchlist")) {
                boolean addedToWatchlist = (boolean) updates.get("addedToWatchlist");

                if (addedToWatchlist) {
                    // If movie was added to watchlist
                    userMovie.setStatus(false);

                } else {
                    // If movie was removed from watchlist
                    userMovieDAO.removeMovieFromUser(user.getId(), movie.getORMID());
                    insertUserMovie = false;
                }
            } else if (updates.containsKey("favourited")) {
                boolean favourite = (boolean) updates.get("favourited");

                if (favourite) {
                    // If movie was added to favourites
                    addMovieFavourite(userMovie, user, movie);

                } else {
                    // If movie was removed from favourites
                    removeMovieFavourite(userMovie, user, movie);

                }
            } else if (updates.containsKey("rating")) {

                // If movie was rated
                Integer newRating = (Integer) updates.get("rating");
                Integer oldRating = userMovie.getRating();

                userMovie.setRating(newRating);

                if (newRating != null) {
                    updateMovieRating(user, movie, oldRating, newRating);
                } else {
                    removeMovieRating(user, movie, oldRating);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        mUserDAO.merge(user);
        movieDAO.merge(movie);
        if (insertUserMovie)
           userMovieDAO.merge(userMovie);
        userService.clearUsersCache(user.getUsername());
        return true;
    }


    private Map<Integer, String> movieHoursAchievements = Map.of(
        10, "10 movie hours",
        50, "50 movie hours",
        100, "100 movie hours",
        500, "500 movie hours",
        1000, "1000 movie hours"
    );


    private void updateMovieHoursAchievements(MUser u, int movieRuntime) {
        int hoursB = u.getMinutesCount() / 60;
        int hoursA = (u.getMinutesCount() + movieRuntime) / 60;
        for(Map.Entry<Integer, String> m: movieHoursAchievements.entrySet()){
            int key = m.getKey();
            if (key > hoursB && key<= hoursA){
                userService.addAchievement(u, m.getValue());
                break;
            }
        }
    }


    private void updateFirstFavouriteAchievement(MUser u) {
        if (u.getFavouriteCount() == 0)
            userService.addAchievement(u, "First favourite movie");
    }


    private void updateFirstRatingAchievement(MUser u) {
        if (u.getRatingsCount() == 0)
            userService.addAchievement(u, "First rating");
    }


    private void updateFirstCommentAchievement(MUser u) {
        if (u.getCommentsCount() == 0)
            userService.addAchievement(u, "First comment");
    }


    private Map<Integer, String> likesAchievements = Map.of(
            1, "1 like single comment",
            10, "10 likes single comment",
            50, "50 likes single comment",
            100, "100 likes single comment",
            500, "500 likes single comment",
            1000, "1000 likes single comment"
    );

    private List<Integer> likesNumber = new ArrayList<>(likesAchievements.keySet()).stream().sorted().collect(Collectors.toList());

    private void updateLikesAchievements(MUser u, int likes) {
        int likesB = likes - 1;
        int likesA = likes;

        int nrLikeBadges = achievementDAO.getMaxLikeBadge(u.getId()) - 1;
        int maxLikeBadge = nrLikeBadges > -1? likesNumber.get(nrLikeBadges) : 0 ;

        if (likesA < maxLikeBadge) return;

        for(Map.Entry<Integer, String> m: likesAchievements.entrySet()){
            int key = m.getKey();
            if (key > likesB && key<= likesA){
                userService.addAchievement(u, m.getValue());
                break;
            }
        }
    }


    @Transactional
    private void addFeedEntry(int feedEntryType, MUser user, int contentId, Date date) {
        Feed f = new Feed();

        f.setType(feedEntryType);
        f.setUser(user);
        f.setIdContent(contentId);
        f.setTimestamp(new Timestamp(date.getTime()));

        feedDAO.persist(f);
    }


    private void updateFeed(Feed f, int feedEntryType, Date date) {
        f.setType(feedEntryType);

        if(date != null) {
            f.setTimestamp(new Timestamp(date.getTime()));
        }

        feedDAO.merge(f);
    }


    @Override
    public Map postComment(Integer movieId, String token, Map<String, Object> content) throws Exception {

        var user = mUserDAO.getSimpleUserByToken(token);
        var movie = movieDAO.loadEntity("tmdb=" + movieId);

        var comment = new Comment();

        comment.setCommenter(user);

        comment.setContent((String) content.get("message"));

        var dateCommented = new Date();

        comment.setTimestamp(new Timestamp(dateCommented.getTime()));
        comment.setLikes(0);
        comment.setMovie(movie);

        commentDAO.persist(comment);
        commentDAO.flush();

        var map = new HashMap<String, Object>();
        map.put("id", comment.getId());
        map.put("userId", user.getId());
        map.put("date", util.formatDate(dateCommented));
        map.put("content", comment.getContent());
        map.put("likes", comment.getLikes());
        map.put("username", user.getUsername());
        map.put("userAvatar", user.getAvatar() != null ? user.getAvatar() : user.getGender() + ".svg");

        addFeedEntry(3, user, comment.getId(), dateCommented);
        updateFirstCommentAchievement(user);
        userService.clearUsersCache(user.getUsername());

        return map;
    }


    @Override
    public Object getMovieComments(Integer movieId, int page, String token) throws Exception {

        List comments;

        if(token != null) {
            Integer id = userService.getUserIdByToken(token);
            comments = commentDAO.getCommentsMovieWithUserLikes(movieId, page * 10, 10, id);
        }
        else {
            comments = commentDAO.getCommentsMovie(movieId, page * 10, 10);
        }

        boolean moreComments = !(comments.size() < 10);

        Map<String, Object> result = new HashMap<>();
        result.put("comments", comments);
        result.put("moreComments", moreComments);

        return result;
    }

    @Override
    @Transactional
    public Object getMovieMembers(Integer movieId, int page, boolean isActor) {
        List members = movieMemberDAO.getMovieMembers(movieId, isActor,page * 20, 20);

        boolean moreMembers = !(members.size() < 20);

        Map<String, Object> result = new HashMap<>();
        result.put("members", members);
        result.put("moreMembers", moreMembers);

        return result;
    }


    @Override
    @Transactional
    public Object getBackdrops(int movieId, int page) {
        List backdrops = mediaDAO.getMovieMedia(movieId, 'b',page * 20, 20);

        boolean moreBackdrops = !(backdrops.size() < 20);

        Map<String, Object> result = new HashMap<>();
        result.put("backdrops", backdrops);
        result.put("moreBackdrops", moreBackdrops);

        return result;
    }


    @Override
    public Object getVideos(int movieId, int page) {
        List videos = mediaDAO.getMovieMedia(movieId, 'v',page * 20, 20);

        boolean moreVideos = !(videos.size() < 20);

        Map<String, Object> result = new HashMap<>();
        result.put("videos", videos);
        result.put("moreVideos", moreVideos);

        return result;
    }


    @Override
    public Object getPosters(int movieId, int page) {
        List posters = mediaDAO.getMovieMedia(movieId, 'p',page * 20, 20);

        boolean morePosters = !(posters.size() < 20);

        Map<String, Object> result = new HashMap<>();
        result.put("posters", posters);
        result.put("morePosters", morePosters);

        return result;
    }

    @Override
    @Transactional
    public boolean likeAComment(Integer id, String token) throws Exception {

        var user = mUserDAO.getSimpleUserByToken(token);
        var comment = commentDAO.findById(id);

        comment.addUpvoter(user);

        commentDAO.merge(comment);
        commentDAO.flush();

        updateLikesAchievements(user, comment.getLikes());

        return true;
    }


    @Override
    @Transactional
    public boolean dislikeComment(Integer id, String token) throws Exception {
        var user = mUserDAO.getSimpleUserByToken(token);
        var comment = commentDAO.findById(id);

        comment.removeUpvoter(user);

        commentDAO.merge(comment);
        commentDAO.flush();

        return true;

    }


	@Override
    @Transactional(readOnly=true)
    public Object getCommentReplies(Integer commentId, int page, String token) throws Exception {
        List replies;

        if(token != null) {
            Integer id = userService.getUserIdByToken(token);
            replies = commentDAO.getRepliesCommentWithUserLikes(commentId, page * 2, 2, id);
        }
        else {
            replies = commentDAO.getRepliesComment(commentId, page * 2, 2);
        }

        boolean moreReplies = !(replies.size() < 2);

        Map<String, Object> result = new HashMap<>();
        result.put("replies", replies);
        result.put("moreReplies", moreReplies);

        return result;
    }


    @Override
    @Transactional
    public Object replyToComment(int id, String token, Map<String, Object> content) throws Exception {
        var user = mUserDAO.getSimpleUserByToken(token);
        var parentComment = commentDAO.findById(id);

        var comment = new Comment();

        comment.setCommenter(user);

        comment.setContent((String) content.get("message"));

        var dateCommented = new Date();

        comment.setTimestamp(new Timestamp(dateCommented.getTime()));
        comment.setLikes(0);
        comment.setParent(id);
        comment.setMovie(parentComment.getMovie());

        commentDAO.persist(comment);
        commentDAO.flush();

        var map = new HashMap<String, Object>();

        map.put("id", comment.getId());
        map.put("userId", user.getId());
        map.put("date", util.formatDate(dateCommented));
        map.put("content", comment.getContent());
        map.put("likes", comment.getLikes());
        map.put("username", user.getUsername());
        map.put("userAvatar", user.getAvatar() != null ? user.getAvatar() : user.getGender() + ".svg");
        map.put("isLiked", false);

        updateFirstCommentAchievement(user);
        userService.clearUsersCache(user.getUsername());
        return map;
    }


    @Override
    public List search(String title, String sort, String genre) {
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

        builder.size(90);
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


    @Override
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


    @Override
    public int estimatedCount() {
        return movieDAO.estimatedSize();
    }


    @Override
    public int estimatedNumberOfComments() {
        return commentDAO.estimatedSize();
    }


    @Override
    public List latestMovies(int begin, int limit) {
        return movieDAO.getLatestMovies(begin, limit);
    }


    @Override
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


    @Override
    public List<Integer> theatersIds() {
        return theaterDAO.findAll()
                .stream()
                .map(Theater::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List randomUpcomingMovies(int limit) {
        return movieDAO.getRandomUpcomingMovies(limit);
    }


    private void addMovieFavourite(UserMovie userMovie, MUser user, Movie movie){
        userMovie.setFavourite(true);
        userMovie.setDateFavourite(new Date());
        addFeedEntry(2, user, movie.getORMID(), new Date());

        updateFirstFavouriteAchievement(user);

        //Aumentar o número de favourites no movie
        movie.addFavouriteCount();
        user.addFavouriteCount();
    }


    private void removeMovieFavourite(UserMovie userMovie, MUser user, Movie movie){
        userMovie.setFavourite(false);
        userMovie.setDateFavourite(null);

        feedDAO.removeFromFeed(user.getId(), movie.getORMID(), 2);

        movie.removeFavouriteCount();
        user.removeFavouriteCount();
    }


    private void addMovieWatched(UserMovie userMovie, MUser user, Movie movie){
        userMovie.setStatus(true);
        userMovie.setDateWatched(new Date());

        int runtime = movie.getRuntime() == null ? 0 : movie.getRuntime();

        updateMovieHoursAchievements(user, runtime);

        movie.addWatchCount();
        user.addMovieCount();
        user.addMinutesCount(runtime);
    }


    private void removeMovieWatchedDependencies(UserMovie userMovie, MUser user, Movie movie){

        feedDAO.removeFromFeed(user.getId(), movie.getORMID());

        movie.removeWatchCount();
        user.removeMovieCount();
        user.removeMinutesCount(movie.getRuntime());

        if (userMovie.getFavourite()) {
            user.removeFavouriteCount();
            movie.removeFavouriteCount();
        }

        if (userMovie.getRating() != null) {
            movie.removeRatingSum(userMovie.getRating());
            movie.removeRatingCount();
            user.removeRatingCount();
        }
    }


    private void clearUserMovie(UserMovie userMovie){
        userMovie.setStatus(false);
        userMovie.setDateWatched(null);
        userMovie.setFavourite(false);
        userMovie.setDateFavourite(null);
        userMovie.setRating(null);
    }


    private void addMovieRating(MUser user, Movie movie, Integer newRating){
        updateFirstRatingAchievement(user);

        movie.addRatingCount();
        movie.addRatingSum(newRating);
        user.addRatingCount();
    }


    private void updateMovieRating(MUser user, Movie movie, Integer oldRating, Integer newRating){
        Feed f = feedDAO.getFeedWithType(user.getId(), movie.getTmdb(), 1);

        if (f != null)
            updateFeed(f, 0, new Date());


        if (oldRating != null) {
            movie.updatingRatingSum(oldRating, newRating);
        }
        else {
            updateFirstRatingAchievement(user);

            movie.addRatingCount();
            movie.addRatingSum(newRating);
            user.addRatingCount();
        }
    }


    private void removeMovieRating(MUser user, Movie movie, Integer oldRating){
        Feed f = feedDAO.getFeedWithType(user.getId(), movie.getTmdb(),0);

        updateFeed(f, 1, null);

        movie.removeRatingCount();
        movie.removeRatingSum(oldRating);
        user.removeRatingCount();
    }


}
