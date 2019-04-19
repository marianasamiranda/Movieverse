package business;

import data.movieverse.*;
import org.orm.PersistentException;
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


public class UsersManager {

    //time until token expires (minutes)
    private static final int TOKENLIMIT = 10000;
    private static final Path AVATARLOCATION = Paths.get("../Frontend/frontend/avatar/");

    private static void save(Object o) {
        try {
            if (o instanceof MUser)
                MUserDAO.save((MUser) o);
            else if (o instanceof Friendship)
                FriendshipDAO.save((Friendship) o);
        }
        catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    private static void refresh(MUser m) {
        try {
            MUserDAO.refresh(m);
        }
        catch (PersistentException e) {
            e.printStackTrace();
        }
    }


    public static MUser getUserByEmail(String email) {
        try {
            return MUserDAO.loadMUserByQuery("email='" + email + "'", "id");
        }
        catch (Exception e) {
            return null;
        }
    }


    public static MUser getUserByUsername(String username) {
        try {
            return MUserDAO.loadMUserByQuery("username='" + username + "'", "id");
        }
        catch (Exception e) {
            return null;
        }
    }


    public static MUser getUserByToken(String token) {
        try {
            return MUserDAO.loadMUserByQuery("token='" + token + "'", "id");
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Friendship getFriendship(int sender, int receiver) {
        try {
            return FriendshipDAO.loadFriendshipByQuery(
                    "muserid=" + sender + "and muserid2=" +  receiver, "id");
        }
        catch (PersistentException e) {
            return null;
        }
    }



    //////////////////////////////////////////////////



    public static String registerUser(String email, String username, String name, String password,
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
        m.setUserCountry(CountryManager.getCountryByCode(country));
        m.setToken(Security.generateToken());
        //m.setTokenLimit(...);
        save(m);

        return m.getToken();
    }


    public static String login(String username, String password) throws Exception {
        MUser u = getUserByUsername(username);

        if (u == null)
            throw new Exception("User doesn't exist");

        if (!Security.checkMatch(password, u.getPassword()))
            throw new Exception("Wrong password");

        u.setToken(Security.generateToken());
        //m.setTokenLimit(...);
        save(u);

        return u.getToken();
    }


    public static void logout(String token) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        u.setToken(null);
        save(u);
    }


    //if username is null get token's owner's info
    public static Map profileInfo(String token, String username) throws Exception {
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
                    friendship = "requested";
                else if (Arrays.asList(u.getReceivedMusers()).stream()
                        .anyMatch(x -> x.getUsername().equals(username)))
                    friendship = "received";
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

        refresh(u);

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
        m.put("statsFriends", 0);//TODO
        m.put("badges", u.getBadges());
        m.put("avatar", u.getAvatar());
        m.put("self", self);
        m.put("friendship", friendship);
        //m.put("recentMovies", u.getMovies()); TODO
        //m.put("favouritesMovies", u.getMovies()) TODO
        //m.put("watchlist", u.getMovies()) TODO
        //m.put("recommended", u.getMovies()) TODO
        //m.put("friends", u.getFriends)
        return m;
    }


    public static String newAvatar(String token, MultipartFile file) throws Exception {
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
        save(m);
        return filename;
    }


    public static void newGenre(String token, String genre) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        Genre g = GenreManager.getGenre(genre);

        if (g == null)
            throw new Exception("No such genre");

        u.setFavouriteGenre(g);
        save(u);
    }


    public static String getAvatar(String token) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        if (u.getAvatar() != null)
            return u.getAvatar();
        else return u.getGender() + ".svg";
    }


    public static List getFriendRequests(String token, String type) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        List<MUser> l;
        if (type.equals("received"))
            l = Arrays.asList(u.getReceivedMusers());
        else
            l = Arrays.asList(u.getRequestedMusers());

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


    public static void newFriendRequest(String token, String username) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        if (u.getUsername().equals(username))
            throw new Exception("Can't send a self request");

        MUser target = getUserByUsername(username);

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
    public static void processRequest(String token, String username, boolean decision) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        MUser target = getUserByUsername(username);

        if (target == null)
            throw new Exception("No such user");

        //reject request
        if (!decision) {
            FriendshipDAO.deleteAndDissociate(getFriendship(target.getId(), u.getId()));
        }
        //add friend
        else {
            //TODO
        }
    }

    //Cancels a sent request
    public static void cancelRequest(String token, String username) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        MUser target = getUserByUsername(username);

        if (target == null)
            throw new Exception("No such user");

        FriendshipDAO.deleteAndDissociate(getFriendship(u.getId(), target.getId()));
    }
}
