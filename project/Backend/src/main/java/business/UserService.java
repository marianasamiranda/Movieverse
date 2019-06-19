package business;

import data.entities.MUser;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface UserService {
    void clearUsersCache(String... usernames);

    String registerUser(String email, String username, String name, String password,
                        String country, LocalDate birthdate, char gender) throws Exception;

    String login(String username, String password) throws Exception;

    void logout(String token) throws Exception;

    Map feedInfo(String token) throws Exception;

    Object profileInfo(String token, String username) throws Exception;

    String newAvatar(String token, MultipartFile file) throws Exception;

    void newGenre(String token, String genre) throws Exception;

    Object getAvatar(String token) throws Exception;

    Object getFriendRequests(String token, String type) throws Exception;

    void newFriendRequest(String token, String username) throws Exception;

    void processRequest(String token, String username, boolean decision) throws Exception;

    void cancelRequest(String token, String username) throws Exception;

    Object movieList(String token, String type, int begin, int limit) throws Exception;

    Object friendsList(String token, int begin, int limit) throws Exception;

    List search(String token, String name) throws Exception;

    int estimatedCount();

    void addAchievement(MUser u, String badgeName);

    Object feedEntries(String token, Integer page) throws Exception;

    Object searchPage(String token) throws Exception;
}
