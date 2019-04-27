package business;

import data.daos.MUserDAO;
import data.entities.Genre;
import data.entities.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import security.Security;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsersManager {

    @Autowired
    private GenreManager genreManager;

    @Autowired
    private CountryManager countryManager;

    @Autowired
    private MUserDAO mUserDAO;

    //time until token expires (minutes)
    private static final int TOKENLIMIT = 10000;
    private static final Path AVATARLOCATION = Paths.get("../Frontend/frontend/avatar/");

    private void save(MUser m) {
       // try {
            mUserDAO.save(m);
        /*}
        catch (PersistentException e) {
            e.printStackTrace();
        }*/
    }


    public MUser getUserByEmail(String email) {
        try {
            return mUserDAO.loadEntity("email='" + email + "'", "id");
        }
        catch (Exception e) {
            return null;
        }
    }


    public MUser getUserByUsername(String username) {
        try {
            return mUserDAO.loadEntity("username='" + username + "'", "id");
        }
        catch (Exception e) {
            return null;
        }
    }


    public MUser getUserByToken(String token) {
        try {
            return mUserDAO.loadEntity("token='" + token + "'", "id");
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
        m.setBirthDate(Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        m.setJoinDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        m.setUserCountry(countryManager.getCountryByCode(country));
        m.setToken(Security.generateToken());
        //m.setTokenLimit(...);
        save(m);

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
        save(u);

        return u.getToken();
    }


    public void logout(String token) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        u.setToken(null);
        save(u);
    }

    public Map profileInfo(String token) throws Exception {
        MUser u = getUserByToken(token);

        if (u == null)
            throw new Exception("Wrong token");

        Map<String, Object> m = new HashMap<>();
        m.put("username", u.getUsername());
        m.put("name", u.getName());
        m.put("gender", u.getGender());
        m.put("birthdate", u.getBirthDate());
        m.put("joindate", u.getJoinDate());
        m.put("country", u.getUserCountry().getAlphaCode());
        m.put("genre", u.getFavouriteGenre().getName());
        m.put("statsMovies", u.getMovieCount());
        m.put("statsHours", u.getHoursCount());
        m.put("statsComments", 0);//TODO
        m.put("statsRatings", 0);//TODO
        m.put("statsFriends", 0);//TODO
        m.put("badges", u.getBadges());
        m.put("avatar", u.getAvatar());
        //m.put("recentMovies", u.getMovies()); TODO
        //m.put("favouritesMovies", u.getMovies()) TODO
        //m.put("watchlist", u.getMovies()) TODO
        //m.put("recommended", u.getMovies()) TODO
        //m.put("friends", u.getFriends)
        return m;
    }

    public String newAvatar(String token, MultipartFile file) throws Exception {
        MUser m = getUserByToken(token);

        if (m == null)
            throw new Exception("Wrong token");

        String path = "../Frontend/frontend/public/avatars/";
        String filename = m.getEmail() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        String p = path + filename;
        Path patha = Path.of(path+filename);
        Files.copy(file.getInputStream(), patha , StandardCopyOption.REPLACE_EXISTING);

        m.setAvatar(filename);
        save(m);
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
        save(u);
    }
}
