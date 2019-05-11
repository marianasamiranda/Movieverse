package business;

import data.daos.FriendshipDAO;
import data.daos.MUserDAO;
import data.entities.Friendship;
import data.entities.Genre;
import data.entities.MUser;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
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
import java.time.ZoneId;
import java.util.*;

@Service
public class UsersManager {

    @Autowired
    private GenreManager genreManager;

    @Autowired
    private CountryManager countryManager;

    @Autowired
    private FriendshipDAO friendshipDAO;

    @Autowired
    private MUserDAO mUserDAO;

    @Autowired
    private RestHighLevelClient client;

    //time until token expires (minutes)
    private final int TOKENLIMIT = 10000;
    private final Path AVATARLOCATION = Paths.get("../Frontend/frontend/avatar/");

    private void save(Object o) {
        if (o instanceof MUser) {
            mUserDAO.persist((MUser) o);
        }
        else if (o instanceof Friendship)
            friendshipDAO.persist((Friendship) o);
    }

    /*
    private void refresh(MUser m) {
        mUserDAO.refresh(m);
    }*/


    public MUser getUserByEmail(String email) {
        try {
            return mUserDAO.loadEntityInitalize("email='" + email + "'", "id");
        }
        catch (Exception e) {
            return null;
        }
    }


    public MUser getUserByUsername(String username) {
        try {
            return mUserDAO.loadEntityInitalize("username='" + username + "'", "id");
        }
        catch (Exception e) {
            return null;
        }
    }

    public MUser getSimpleUserByUsername(String username) {
        try {
            return mUserDAO.loadEntity("username='" + username + "'", "id");
        }
        catch (Exception e) {
            return null;
        }
    }



    public MUser getUserByToken(String token) {
        try {
            return mUserDAO.loadEntityInitalize("token='" + token + "'", "id");
        }
        catch (Exception e) {
            return null;
        }
    }

    public MUser getSimpleUserByToken(String token) {
        try {
            return mUserDAO.loadEntity("token='" + token + "'", "id");
        }
        catch (Exception e) {
            return null;
        }
    }

    public Friendship getFriendship(int sender, int receiver) {
        try {
            return friendshipDAO.loadEntity(
                    "sender=" + sender + "and receiver= " +  receiver, "id");
        }
        catch (Exception e) {
            return null;
        }
    }



    //////////////////////////////////////////////////


    public String registerUser(String email, String username, String name, String password,
                                       String country, LocalDate birthdate, char gender) throws Exception {
        if (getUserByUsername(username) != null)
            throw new Exception("Username already registered");

        if (getUserByEmail(email) != null)
            throw new Exception("Email already registered");

        MUser m = new MUser();
        m.setEmail(email);
        m.setUsername(username);
        m.setName(name);
        m.setPassword(Security.encode(password));
        m.setGender(gender);
        m.setBirthDate(Util.localDateToDate(birthdate));
        m.setJoinDate(Util.localDateToDate(LocalDate.now()));
        m.setUserCountry(countryManager.getCountryByCode(country));
        m.setToken(Security.generateToken());
        //m.setTokenLimit(...);
        save(m);

        //elasticsearch
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("name", name);
        map.put("country", country);
        map.put("avatar", gender + ".svg");
        IndexRequest request = new IndexRequest("movieverse_users").id(m.getId() + "").source(map);
        client.index(request, RequestOptions.DEFAULT);

        return m.getToken();
    }


    public String login(String username, String password) throws Exception {
        MUser u = getUserByUsername(username);

        if (u == null)
            throw new Exception("User doesn't exist");

        if (!Security.checkMatch(password, u.getPassword()))
            throw new Exception("Wrong password");

        u.setToken(Security.generateToken());
        //m.setTokenLimit(...);
        mUserDAO.merge(u);

        return u.getToken();
    }


    public void logout(String token) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        u.setToken(null);
        mUserDAO.merge(u);
    }


    //if username is null get token's owner's info
    public Map profileInfo(String token, String username) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");
        boolean self = false;
        String friendship = null;
        if (username != null) {
            if (u.getUsername().equals(username))
                self = true;
            else { //TODO não buscar tudo à base de dados
                if (Arrays.asList(u.getRequestedMusers()).stream()
                        .anyMatch(x -> x.getUsername().equals(username)))
                    friendship = "received";
                else if (Arrays.asList(u.getReceivedMusers()).stream()
                        .anyMatch(x -> x.getUsername().equals(username)))
                    friendship = "requested";
                //TODO
                //else if (Arrays.asList(u.getFriends()) ...)
                //friendship = "friends"
                else
                    friendship = null;
                u = getUserByUsername(username);
                if (u == null)
                    throw new Exception("User doesn't exists");
            }
        }

        //refresh();

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
        m.put("statsComments", 0);//TODO
        m.put("statsRatings", 0);//TODO
        m.put("statsFriends", u.getFriendsCount());//TODO
        m.put("badges", u.getBadges());
        m.put("avatar", u.getAvatar());
        m.put("self", self);
        m.put("friendship", friendship);
        //m.put("recentMovies", u.getMovies()); TODO
        //m.put("favouritesMovies", u.getMovies()) TODO
        //m.put("watchlist", u.getMovies()) TODO
        //m.put("recommended", u.getMovies()) TODO
        m.put("friends", new ArrayList<>());
        for (var f: u.getFriends()) {
            MUser friend = ((Friendship) f).getRequestedMuser();
            Map map = new HashMap<>();
            map.put("username", friend.getUsername());
            map.put("avatar", friend.getAvatar() != null ? friend.getAvatar() : friend.getGender() + ".svg");
            ((List) m.get("friends")).add(map);
        }

        return m;
    }


    public String newAvatar(String token, MultipartFile file) throws Exception {
        MUser m = getUserByToken(token);

        if (m == null)
            throw new Exception("Wrong token");

        String path = "../Frontend/frontend/public/avatars/";
        String filename = m.getUsername() + "." +
                StringUtils.getFilenameExtension(file.getOriginalFilename());
        String p = path + filename;
        Path patha = Path.of(path+filename);
        Files.copy(file.getInputStream(), patha , StandardCopyOption.REPLACE_EXISTING);

        m.setAvatar(filename);
        mUserDAO.merge(m);

        //elasticsearch
        Map<String, Object> map = new HashMap<>();
        map.put("avatar", filename);
        UpdateRequest request = new UpdateRequest("movieverse_users", m.getId() + "").doc(map);
        client.update(request, RequestOptions.DEFAULT);

        return filename;
    }


    public void newGenre(String token, String genre) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        Genre g = genreManager.getGenre(genre);

        if (g == null)
            throw new Exception("No such genre");

        u.setFavouriteGenre(g);
        mUserDAO.merge(u);
    }


    public String getAvatar(String token) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        if (u.getAvatar() != null)
            return u.getAvatar();
        else return u.getGender() + ".svg";
    }


    public List getFriendRequests(String token, String type) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        List<MUser> l;
        if (type.equals("received"))
            l = Arrays.asList(u.getRequestedMusers());
        else
            l = Arrays.asList(u.getReceivedMusers());

        List r = new ArrayList();
        for (MUser mu: l) {
            Map m = new HashMap();
            m.put("username", mu.getUsername());
            m.put("name", mu.getName());
            m.put("country", mu.getUserCountry().getAlphaCode());
            m.put("avatar", mu.getAvatar() != null ? mu.getAvatar() : mu.getGender() + ".svg");
            m.put("common", 0); //TODO encontrar número de amigos em comum
            r.add(m);
        }

        return r;
    }


    public void newFriendRequest(String token, String username) throws Exception {
        MUser u = getSimpleUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        if (u.getUsername().equals(username))
            throw new Exception("Can't send a self request");

        MUser target = getSimpleUserByUsername(username);

        if (target == null)
            throw new Exception("No such user");

        Friendship f = new Friendship();
        f.setRequestedMuser(u);
        f.setReceivedMuser(target);
        f.setDate(Util.localDateToDate(LocalDate.now()));
        f.setPending(true);
        save(f);
    }

    //Cancels a sent request
    public void processRequest(String token, String username, boolean decision) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        MUser target = getUserByUsername(username);

        if (target == null)
            throw new Exception("No such user");

        //reject request
        if (!decision) {
            friendshipDAO.removeEntity("sender=" + target.getId() + "and receiver= " +  u.getId());
        }
        //add friend
        else {
            Friendship friendship = friendshipDAO.loadEntity("sender=" + target.getId() + "and receiver= " +  u.getId());
            friendship.setReceivedMuser(null);
            friendship.setRequestedMuser(null);
            friendship.setPending(false);
            friendship.setDate(Util.localDateToDate(LocalDate.now()));

            u.addFriend(friendship);
            target.addFriend(friendship);

            mUserDAO.merge(u);
            mUserDAO.merge(target);
            friendshipDAO.merge(friendship);
        }
    }

    //Cancels a sent request
    public void cancelRequest(String token, String username) throws Exception {
        MUser u = getSimpleUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        MUser target = getSimpleUserByUsername(username);

        if (target == null)
            throw new Exception("No such user");

        friendshipDAO.removeEntity("sender=" + u.getId() + "and receiver= " +  target.getId());
    }


    public List search(String token, String name) throws Exception {
        if (getUserByToken(token) == null)
            throw new Exception("Wrong token");

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
        var response = client.search(search, RequestOptions.DEFAULT);

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
