package business;

import data.daos.CompanyDAO;
import data.daos.MovieDAO;
import data.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private MovieDAO movieDAO;



    public Object companyInfo(int id) {

        Company m = companyDAO.findById(id);

        List<Map<String, Object>> moviesInfo = movieDAO.getCompanyMoviesFromTo(id, 0, 50);
        boolean moreMovies = !(moviesInfo.size() < 50);

        Map<String, Object> info = new HashMap<>();
        info.put("description", m.getDescription());
        info.put("image", "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + m.getImage());
        info.put("name", m.getName());
        info.put("movies", moviesInfo);
        info.put("homepage", m.getHomepage());
        info.put("headquarters", m.getHeadquarters());
        info.put("country", m.getCompanyCountry().getName());
        info.put("moreMovies", moreMovies);

        return info;

    }

    public Object companyMovies(int id, int page) {
        List<Map<String, Object>> moviesInfo = movieDAO.getCompanyMoviesFromTo(id, page * 50, 50);
        boolean moreMovies = !(moviesInfo.size() < 50);

        Map<String, Object> info = new HashMap<>();
        info.put("movies", moviesInfo);
        info.put("moreMovies", moreMovies);

        return info;
    }

}