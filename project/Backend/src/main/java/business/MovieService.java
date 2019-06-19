package business;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MovieService {
    Map<String, Object> get(Integer id) throws Exception;

    Map<String, Object> getShort(Integer id) throws Exception;

    HashMap<Object, Object> getMovieMeInfo(String token, Integer movieId) throws Exception;

    boolean patchMovieMeInfo(String token, Integer movieId, Map<String, Object> updates) throws Exception;

    Map postComment(Integer movieId, String token, Map<String, Object> content) throws Exception;

    Object getMovieComments(Integer movieId, int page, String token) throws Exception;

    Object getMovieMembers(Integer movieId, int page, boolean isActor);

    Object getBackdrops(int movieId, int page);

    Object getVideos(int movieId, int page);

    Object getPosters(int movieId, int page);

    boolean likeAComment(Integer id, String token) throws Exception;

    boolean dislikeComment(Integer id, String token) throws Exception;

    Object getCommentReplies(Integer commentId, int page, String token) throws Exception;

    Object replyToComment(int id, String token, Map<String, Object> content) throws Exception;

    List search(String title, String sort, String genre) throws IOException;

    Object showtimes(int theater);

    int estimatedCount();

    int estimatedNumberOfComments();

    List latestMovies(int begin, int limit);

    Object movieSearchPage();

    List<Integer> theatersIds();

    List randomUpcomingMovies(int limit);
}
