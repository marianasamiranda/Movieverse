package business;

import data.ElasticSearch;
import data.RedisCache;
import data.daos.FriendshipDAO;
import data.daos.MUserDAO;
import data.entities.Friendship;
import data.entities.Genre;
import data.entities.MUser;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.*;

@Service
public class UsersManager {

    @Autowired
    private GenreManager genreManager;

    @Autowired
    private MovieManager movieManager;

    @Autowired
    private CountryManager countryManager;

    @Autowired
    private FriendshipDAO friendshipDAO;

    @Autowired
    private MUserDAO mUserDAO;

    @Autowired
    private Util util;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ElasticSearch elasticSearch;

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


    private void clearUsersCache(String... usernames) {
        List<String> keys = new ArrayList<>();
        for (String username : usernames) {
            keys.add("profile_" + username);
            keys.add("avatar_" + username);
        }
        redisCache.delAll(keys.toArray(new String[0]));
    }


    private void checkUserAvailable(String username, String email) throws Exception {
        if (mUserDAO.getUserByUsername(username) != null)
            throw new Exception("Username already registered");

        if (mUserDAO.getUserByEmail(email) != null)
            throw new Exception("Email already registered");
    }


    //Public methods

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
        u.setUserCountry(countryManager.getCountryByCode(country));
        u.setToken(Security.generateToken());
        //u.setTokenLimit(util.localDateTimeToDateTime(LocalDateTime.now().plusSeconds(TOKENLIMIT)));
        save(u);

        elasticSearch.addUser(u);

        return u.getToken();
    }


    public String login(String username, String password) throws Exception {
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


    public void logout(String token) throws Exception {
        MUser u = mUserDAO.validateToken(token);
        u.setToken(null);
        mUserDAO.merge(u);
    }


    public Map feedInfo(String token, String username) throws Exception {
        MUser u = mUserDAO.validateToken(token);
        boolean self = false;

        Map<String, Object> m = new HashMap<>();
        m.put("upcomingMovies", movieManager.randomUpcomingMovies(4));
        m.put("self", self);

        return m;
    }


    @Transactional
    public Object profileInfo(String token, String username) throws Exception {
        MUser u = mUserDAO.validateToken(token);

        if (username == null) {
            String cachedInfo = redisCache.get("profile_" + u.getUsername());
            //in cache
            if (cachedInfo != null)
                return cachedInfo;
        }

        boolean self = false;
        String friendship = null;

        if (username != null) {
            if (u.getUsername().equals(username))
                self = true;

            //friendship status
            ///TODO função à parte
            else {
                if (mUserDAO.listReceivedMUser(u.getId())
                            .stream()
                            .anyMatch(x -> x.getUsername().equals(username)))
                    friendship = "received";

                else if (mUserDAO.listRequestedMUser(u.getId())
                                 .stream()
                                 .anyMatch(x -> x.getUsername().equals(username)))
                    friendship = "requested";

                else if (mUserDAO.listFriends(u.getId()).stream()
                                .anyMatch(x -> x.getUsername().equals(username)))
                    friendship = "friends";

                else
                    friendship = null;

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
        m.put("statsHours", u.getHoursCount());
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

        m.put("recent", movies.get("recent"));
        m.put("favourite", movies.get("favourite"));
        m.put("watchlist", movies.get("watchlist"));
        m.put("recommended", movies.get("recommended"));

        //add response to redis cache
        if (username == null)
            redisCache.set("profile_" + u.getUsername(), util.toJson(m));

        return m;
    }


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


    public void newGenre(String token, String genre) throws Exception {
        MUser u = mUserDAO.validateToken(token);
        Genre g = genreManager.getGenre(genre);

        if (g == null)
            throw new Exception("No such genre");

        u.setFavouriteGenre(g);
        mUserDAO.merge(u);

        //clear cache
        clearUsersCache(u.getUsername());
    }


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
        m.put("requests", u.getRequestedFriendships().size() > 0);

        //add to redis cache
        redisCache.set("avatar_" + u.getUsername(), util.toJson(m));

        return m;
    }


    @Transactional
    public List getFriendRequests(String token, String type) throws Exception {
        MUser u = mUserDAO.validateToken(token);

        List<MUser> l;
        if (type.equals("received"))
            l = mUserDAO.listReceivedMUser(u.getId());
        else
            l = mUserDAO.listRequestedMUser(u.getId());

        List r = new ArrayList();
        for (MUser mu: l) {
            Map m = new HashMap();
            m.put("username", mu.getUsername());
            m.put("name", mu.getName());
            m.put("country", mu.getUserCountry().getAlphaCode());
            m.put("avatar", mu.getAvatar() != null ? mu.getAvatar() : mu.getGender() + ".svg");
            m.put("common", calculateMutualFriends(u,mu)); //TODO encontrar número de amigos em comum
            r.add(m);
        }

        return r;
    }


    public int calculateMutualFriends(MUser muser1, MUser muser2) {
        List<Integer> friends1 = (List<Integer>) muser1.getFriends().stream().map(t -> {
            int fRequested = ((Friendship) t).getRequestedMuser().getId();
           if ( fRequested != ((Friendship) t).getId()) {
                return fRequested;
            }
            else {
                return ((Friendship) t).getRequestedMuser().getId();
           }
        }).collect(toList());
        List<Integer> friends2 = (List<Integer>) muser2.getFriends().stream().map(t -> {
            int fRequested = ((Friendship) t).getRequestedMuser().getId();
           if ( fRequested != ((Friendship) t).getId()) {
                return fRequested;
            }
            else {
                return ((Friendship) t).getRequestedMuser().getId();
           }
        }).collect(toList());

        friends1.retainAll(friends2);

        return friends1.size();
    }


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

    @Transactional
    //Cancels a sent request
    public void processRequest(String token, String username, boolean decision) throws Exception {
        MUser u = mUserDAO.validateToken(token);
        MUser target = mUserDAO.getUserByUsername(username);

        if (target == null)
            throw new Exception("No such user");

        //reject request
        if (!decision) {
            friendshipDAO.removeEntity("sender=" + target.getId() + "and receiver= " +  u.getId());
        }
        //add friend
        else {
            Friendship friendship = friendshipDAO.loadEntity("sender=" + target.getId() + "and receiver= " +  u.getId());
            friendship.setPending(false);
            friendship.setDate(util.localDateToDate(LocalDate.now()));

            u.addFriend(friendship);
            target.addFriend(friendship);

            mUserDAO.merge(u);
            mUserDAO.merge(target);
            friendshipDAO.merge(friendship);
        }

        //clear cache
        clearUsersCache(u.getUsername(), target.getUsername());
    }


    //Cancels a sent request
    public void cancelRequest(String token, String username) throws Exception {
        MUser u = mUserDAO.getSimpleUserByToken(token);
        MUser target = mUserDAO.getSimpleUserByUsername(username);

        if (target == null)
            throw new Exception("No such user");

        friendshipDAO.removeEntity("sender=" + u.getId() + "and receiver= " +  target.getId());

        //clear cache
        clearUsersCache(u.getUsername(), target.getUsername());
    }


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


    public int estimatedCount() {
        return mUserDAO.estimatedSize();
    }


}
