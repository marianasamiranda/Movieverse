package business.impl;

import business.*;
import data.ElasticSearch;
import data.RedisCache;
import data.daos.*;
import data.entities.*;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import security.Security;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private GenreService genreService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private FriendshipDAO friendshipDAO;

    @Autowired
    private MUserDAO mUserDAO;

    @Autowired
    private BadgeDAO badgeDAO;

    @Autowired
    private AchievementDAO achievementDAO;

    @Autowired
    private Util util;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ElasticSearch elasticSearch;

    @Autowired
    private Environment environment;


    //time until token expires (minutes)
    private final int TOKENLIMIT = 10000;
    private final Path AVATARLOCATION = Paths.get("../Frontend/frontend/avatar/");


    //Private methods

    private void save(Object o) {
        if (o instanceof MUser) {
            mUserDAO.persist((MUser) o);
        }
        else if (o instanceof Friendship)
            friendshipDAO.persist((Friendship) o);
    }


    @Override
    public void clearUsersCache(String... usernames) {
        List<String> keys = new ArrayList<>();
        for (String username : usernames) {
            keys.add("profile_" + username);
            keys.add("avatar_" + username);
            keys.add("requests_sent_" + username);
            keys.add("requests_received_" + username);
        }
        redisCache.delAll(keys.toArray(new String[0]));
    }


    private void checkUserAvailable(String username, String email) throws Exception {
        if (mUserDAO.getUserByUsername(username) != null || username.equals("admin"))
            throw new Exception("Username already registered");

        if (mUserDAO.getUserByEmail(email) != null)
            throw new Exception("Email already registered");
    }


    //Public methods

    @Override
    public String registerUser(String email, String username, String name, String password,
                               String country, LocalDate birthdate, char gender) throws Exception {
        checkUserAvailable(username, email);

        MUser u = new MUser();
        u.setEmail(email);
        u.setUsername(username);
        u.setName(name);
        u.setPassword(Security.encode(password));
        u.setGender(gender);
        u.setBirthDate(util.localDateToDate(birthdate));
        u.setJoinDate(util.localDateToDate(LocalDate.now()));
        u.setUserCountry(countryService.getCountryByCode(country));
        u.setToken(Security.generateToken());
        //u.setTokenLimit(util.localDateTimeToDateTime(LocalDateTime.now().plusSeconds(TOKENLIMIT)));
        save(u);

        elasticSearch.addUser(u);

        return u.getToken();
    }


    private String loginAdmin(String password) throws Exception {
        if (password.equals(environment.getProperty("admin_password"))) {
            String token = Security.generateToken();
            redisCache.set("admin_token", token);
            return token;
        }
        else
            throw new Exception("Wrong Password");
    }

    @Override
    @Transactional
    public String login(String username, String password) throws Exception {
        if (username.equals("admin"))
            return loginAdmin(password);

        MUser u = mUserDAO.getUserByUsername(username);

        if (u == null)
            throw new Exception("User doesn't exist");

        if (!Security.checkMatch(password, u.getPassword()))
            throw new Exception("Wrong password");

        u.setToken(Security.generateToken());
        //u.setTokenLimit(util.localDateTimeToDateTime(LocalDateTime.now().plusSeconds(TOKENLIMIT)));
        mUserDAO.merge(u);

        return u.getToken();
    }

    @Override
    public void logout(String token) throws Exception {
        String adminToken = redisCache.get("admin_token");
        if (adminToken != null && adminToken.equals(token))
            redisCache.del("admin_token");
        else {
            MUser u = mUserDAO.validateToken(token);
            u.setToken(null);
            mUserDAO.merge(u);
        }
    }


    @Override
    public Map feedInfo(String token) throws Exception {
        MUser u = mUserDAO.validateToken(token);
        boolean self = false;

        List<Map> feedEntries = mUserDAO.getFeedEntries(u.getId(), 0, 50);
        boolean moreEntries = !(feedEntries.size() < 50);

        Map<String, Object> m = new HashMap<>();
        m.put("upcomingMovies", movieService.randomUpcomingMovies(4));
        m.put("feedEntries", feedEntries);
        m.put("moreEntries", moreEntries);
        m.put("self", self);

        return m;
    }


    @Override
    @Transactional
    public Object profileInfo(String token, String username) throws Exception {
        MUser u = mUserDAO.validateToken(token);

        if (username == null) {
            String cachedInfo = redisCache.get("profile_" + u.getUsername());
            if (cachedInfo != null) // in cache
                return cachedInfo;
        }

        boolean self = false;
        String friendship = null;

        if (username != null) {
            if (u.getUsername().equals(username))
                self = true;
            //friendship status
            else {
                friendship = friendshipDAO.friendshipStatus(u.getId(),username);
                u = mUserDAO.getUserByUsername(username);
                if (u == null)
                    throw new Exception("User doesn't exists");
            }
        }

        Map<String, Object> m = new HashMap<>();
        m.put("username", u.getUsername());
        m.put("name", u.getName());
        m.put("gender", u.getGender());
        m.put("birthdate", u.getBirthDate());
        m.put("joindate", u.getJoinDate());
        m.put("country", u.getUserCountry().getAlphaCode());
        m.put("genre", u.getFavouriteGenre() != null ? u.getFavouriteGenre().getName() : null);
        m.put("statsMovies", u.getMovieCount());
        m.put("statsHours", u.getMinutesCount()/60);
        m.put("statsComments", u.getCommentsCount());
        m.put("statsRatings", u.getRatingsCount());
        m.put("statsFriends", u.getFriendsCount());
        m.put("badges", u.getBadges());
        m.put("avatar", u.getAvatar());
        m.put("self", self);
        m.put("friendship", friendship);

        m.put("friends", new ArrayList<>());
        List<MUser> friends = mUserDAO.listFriends(u.getId());
        for (var friend: friends.subList(0, Math.min(24, friends.size()))) {
            Map map = new HashMap<>();
            map.put("username", friend.getUsername());
            map.put("avatar", friend.getAvatar() != null ? friend.getAvatar() : friend.getGender() + ".svg");
            ((List) m.get("friends")).add(map);
        }

        //movies
        userMoviesProfileInfo(u, m);

        //add response to redis cache
        if (username == null)
            redisCache.set("profile_" + u.getUsername(), util.toJson(m));

        return m;
    }

    //add movie info to the profile map
    private void userMoviesProfileInfo(MUser u, Map<String, Object> profileInfo) {
        Map<String, List<Map>> movies;
        movies = mUserDAO.allMovieTypes(u.getId(), 0, 24)
                .stream()
                .collect(groupingBy(x -> ((String) x.get("type"))));

        if (!movies.containsKey("recent"))
            movies.put("recent", List.of());

        if (!movies.containsKey("favourite"))
            movies.put("favourite", List.of());

        if (!movies.containsKey("watchlist"))
            movies.put("watchlist", List.of());

        if (!movies.containsKey("recommended"))
            movies.put("recommended", List.of());

        profileInfo.put("recent", movies.get("recent"));
        profileInfo.put("favourite", movies.get("favourite"));
        profileInfo.put("watchlist", movies.get("watchlist"));
        profileInfo.put("recommended", movies.get("recommended"));
    }


    @Override
    public String newAvatar(String token, MultipartFile file) throws Exception {
        MUser u = mUserDAO.validateToken(token);

        String path = "../Frontend/frontend/public/avatars/";
        String filename = u.getUsername() + "." +
                StringUtils.getFilenameExtension(file.getOriginalFilename());
        String p = path + filename;
        Path patha = Path.of(path+filename);
        Files.copy(file.getInputStream(), patha , StandardCopyOption.REPLACE_EXISTING);

        u.setAvatar(filename);
        mUserDAO.merge(u);
        clearUsersCache(u.getUsername());
        elasticSearch.updateAvatar(u);

        return filename;
    }


    @Override
    public void newGenre(String token, String genre) throws Exception {
        MUser u = mUserDAO.validateToken(token);
        Genre g = genreService.getGenre(genre);

        if (g == null)
            throw new Exception("No such genre");

        u.setFavouriteGenre(g);
        mUserDAO.merge(u);

        //clear cache
        clearUsersCache(u.getUsername());
    }


    @Override
    @Transactional
    public Object getAvatar(String token) throws Exception {
        MUser u = mUserDAO.validateToken(token);

        String cachedInfo = redisCache.get("avatar_" + u.getUsername());
        if (cachedInfo != null)
            return cachedInfo;

        Map m = new HashMap();
        if (u.getAvatar() != null)
            m.put("img", u.getAvatar());
        else
            m.put("img", u.getGender() + ".svg");
        m.put("requests", mUserDAO.listReceivedMUser(u.getId()).size() > 0);

        //add to redis cache
        redisCache.set("avatar_" + u.getUsername(), util.toJson(m));

        return m;
    }


	@Override
    @Transactional(readOnly=true)
    public Object getFriendRequests(String token, String type) throws Exception {
        MUser u = mUserDAO.validateToken(token);

        List r = new ArrayList();

        if (type.equals("received")) {
            String cachedInfo = redisCache.get("requests_received_" + u.getUsername());
            if (cachedInfo != null)
                return cachedInfo;

             r = friendshipDAO.requestsReceived(u.getId());
             redisCache.set("requests_received_" + u.getUsername(), util.toJson(r));
        }
        else {
            String cachedInfo = redisCache.get("requests_sent_" + u.getUsername());
            if (cachedInfo != null)
                return cachedInfo;

            r = friendshipDAO.requestsSent(u.getId());
            redisCache.set("requests_sent_" + u.getUsername(), util.toJson(r));
        }

        return r;
    }


    @Override
    public void newFriendRequest(String token, String username) throws Exception {
        MUser u = mUserDAO.getSimpleUserByToken(token);

        if (u.getUsername().equals(username))
            throw new Exception("Can't send a self request");

        MUser target = mUserDAO.getSimpleUserByUsername(username);

        if (target == null)
            throw new Exception("No such user");

        Friendship f = new Friendship();
        f.setRequestedMuser(u);
        f.setReceivedMuser(target);
        f.setDate(util.localDateToDate(LocalDate.now()));
        f.setPending(true);
        save(f);

        //clear cache
        clearUsersCache(u.getUsername(), target.getUsername());
    }

    @Override
    @Transactional
    //Cancels a sent request
    public void processRequest(String token, String username, boolean decision) throws Exception {
        MUser u = mUserDAO.validateToken(token);
        MUser target = mUserDAO.getUserByUsername(username);

        if (target == null)
            throw new Exception("No such user");

        //reject request
        if (!decision)
            friendshipDAO.removeFriendship(target.getId(), u.getId());

        //add friend
        else {
            Friendship friendship = friendshipDAO.getFriendship(target.getId(), u.getId());
            friendship.setPending(false);
            friendship.setDate(util.localDateToDate(LocalDate.now()));

            u.addFriend(friendship);
            target.addFriend(friendship);

            updateFriendsAchievements(u);
            updateFriendsAchievements(target);

            mUserDAO.merge(u);
            mUserDAO.merge(target);
            friendshipDAO.merge(friendship);
        }

        //clear cache
        clearUsersCache(u.getUsername(), target.getUsername());
    }

    private Map<Integer, String> friendsAchievements = Map.of(
        1, "1 friend",
        5, "5 friends",
        15, "15 friends",
        50, "50 friends",
        100, "100 friends"
    );

    private void updateFriendsAchievements(MUser u) {
        String badgeName = friendsAchievements.get(u.getFriendsCount() + 1);
        if (badgeName != null)
            addAchievement(u, badgeName);
    }


    //Cancels a sent request
    @Override
    public void cancelRequest(String token, String username) throws Exception {
        MUser u = mUserDAO.getSimpleUserByToken(token);
        MUser target = mUserDAO.getSimpleUserByUsername(username);

        if (target == null)
            throw new Exception("No such user");

        friendshipDAO.removeFriendship( u.getId(), target.getId());

        //clear cache
        clearUsersCache(u.getUsername(), target.getUsername());
    }


    @Override
    public Object movieList(String token, String type, int begin, int limit) throws Exception {
        MUser u = mUserDAO.validateToken(token);

        List results;
        switch (type) {
            case "recent":
                results = mUserDAO.recentMovies(u.getId(), begin, limit);
                break;
            case "favourites":
                results = mUserDAO.favouriteMovies(u.getId(), begin, limit);
                break;
            case "watchlist":
                results = mUserDAO.watchlist(u.getId(), begin, limit);
                break;
            case "recommended":
                results = mUserDAO.recommendedMovies(u.getId(), begin, limit);
                break;
            default:
                throw new Exception("No such type");
        }

        return results;
    }


    @Override
    @Transactional
    public Object friendsList(String token, int begin, int limit) throws Exception {
        MUser u = mUserDAO.validateToken(token);
        List<MUser> friends = mUserDAO.listFriends(u.getId(), begin, limit);
        List<Map> l = new ArrayList<>();

        friends.forEach(x -> {
            Map m = new HashMap();
            m.put("username", x.getUsername());
            m.put("avatar", x.getAvatar() != null ? x.getAvatar() : x.getGender() + ".svg");
            l.add(m);
        });

        return l;
    }


    @Override
    public List search(String token, String name) throws Exception {
        mUserDAO.validateToken(token);

        if (name == null || name.equals(""))
            return null;

        var search = new SearchRequest("movieverse_users");
        var builder = new SearchSourceBuilder();
        var boolQuery = QueryBuilders.boolQuery();

        for (var n: name.split("\\s+")) {
            boolQuery.should(QueryBuilders.fuzzyQuery("username", n));
            boolQuery.should(QueryBuilders.fuzzyQuery("name", n));
        }

        builder.query(boolQuery);
        builder.size(30);
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
    public int estimatedCount() {
        return mUserDAO.estimatedSize();
    }


    @Override
    public void addAchievement(MUser u, String badgeName) {
        if (!mUserDAO.hasBadge(u.getUsername(), badgeName)) {
            Badge b = badgeDAO.getBadgeByName(badgeName);
            Achievement a = new Achievement();
            a.setDate(util.localDateToDate(LocalDate.now()));
            a.setmUser(u);
            a.setBadge(b);
            achievementDAO.persist(a);
        }
    }


    @Override
    public Object feedEntries(String token, Integer page) throws Exception{
        MUser u = mUserDAO.validateToken(token);

        List<Map> feedEntries = mUserDAO.getFeedEntries(u.getId(), page * 50, 50);
        boolean moreEntries = !(feedEntries.size() < 50);

        Map<String, Object> res = new HashMap<>();
        res.put("entries", feedEntries);
        res.put("moreEntries", moreEntries);

        return res;
    }


    @Override
    public Object searchPage(String token) throws Exception {
        mUserDAO.validateToken(token);

        String cached = redisCache.get("usersSearchPageInfo");
        if (cached != null)
            return cached;

        List<Map> top = mUserDAO.topUpvotedUsers(48);
        top.forEach(x -> {
            if (x.get("avatar") == null)
                x.put("avatar", x.get("gender") + ".svg");
            x.remove("gender");
        });

        String json = util.toJson(top);
        redisCache.set("usersSearchPageInfo", json);
        redisCache.set("usersSearchPageInfo_date", Long.toString(util.unixTimeSeconds() + 3600)); //1 hour

        return top;
    }
}
