package business.impl;

import business.GenreService;
import data.daos.GenreDAO;
import data.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDAO genreDAO;

    @Override
    public Genre getGenre(String name) {
        return genreDAO.loadEntity("name='" + name + "'", "id");

    }
}
