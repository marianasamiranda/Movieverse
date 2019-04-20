package business;

import data.movieverse.Genre;
import data.movieverse.GenreDAO;
import org.orm.PersistentException;

public class GenreManager {

    public static Genre getGenre(String name) {
        try {
            return (Genre) GenreDAO.queryGenre("name='" + name + "'", "id").get(0);
        }
        catch (PersistentException e) {
            return null;
        }
    }
}
