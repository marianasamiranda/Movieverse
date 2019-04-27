package business;

import data.daos.GenreDAO;
import data.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreManager {

    @Autowired
    private GenreDAO genreDAO;

    public Genre getGenre(String name) {
        return genreDAO.loadEntity("name='" + name + "'", "id");

    }
}
