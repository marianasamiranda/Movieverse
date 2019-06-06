package business;

import data.daos.MediaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MediaService {
    @Autowired
    private MediaDAO mediaDAO;

    public Map<String, Object>  getMedia(int movieId) {
        HashMap<String, Object> result = new HashMap<>();
        Map media = mediaDAO.getMovieMedia(movieId);
        result.put("videos", media.get('v'));
        result.put("backdrops", media.get('b'));
        result.put("posters", media.get('p'));
        return result;
    }
}
