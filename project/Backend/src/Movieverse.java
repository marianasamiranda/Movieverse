import data.movieverse.Genre;
import data.movieverse.GenreDAO;
import org.orm.PersistentException;

public class Movieverse {


    public static void main(String[] args) {
        Genre user = new Genre();

        try {
            GenreDAO.save(user);
        } catch (PersistentException e) {
        }

    }



}
