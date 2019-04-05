package business;

import data.movieverse.Country;
import data.movieverse.MUser;
import data.movieverse.MUserDAO;
import org.orm.PersistentException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class UsersManager {

    private static void save(MUser m) {
        try {
            MUserDAO.save(m);
        }
        catch (PersistentException e) {
            e.printStackTrace();
        }
    }


    public static MUser getUserByEmail(String email) {
        try {
            return (MUser) MUserDAO.queryMUser("email='" + email + "'", "id").get(0);
        }
        catch (Exception e) {
            return null;
        }
    }


    public static MUser getUserByUsername(String username) {
        try {
            return (MUser) MUserDAO.queryMUser("username='" + username + "'", "id").get(0);
        }
        catch (Exception e) {
            return null;
        }
    }


    public static String registerUser(String email, String username, String name, String password,
                                       String country, LocalDate birthdate, char gender) {
        if (getUserByUsername(username) != null)
            return "Username already registered";

        if (getUserByEmail(email) != null)
            return "Email already registered";

        MUser m = new MUser();
        m.setEmail(email);
        m.setUsername(username);
        m.setName(name);
        m.setPassword(password);
        m.setGender(gender == 'F');
        m.setBirthDate(Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Country c = CountryManager.getCountryByCode(country);
        m.setUser_country(c);
        save(m);

        return null;
    }


    public static String login(String username, String password) {
        MUser u = getUserByUsername(username);

        if (u == null)
            return "User doesn't exist";

        if (!u.getPassword().equals(password))
            return "Wrong password";

        return null;
    }
}
