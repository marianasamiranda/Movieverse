
package business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class Util {

    private ObjectMapper objectMapper;

    public Util() {
        objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
    }

    public Date localDateToDate(LocalDate d){
        return Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public List<Map> queryListToListMap(List<Object[]> objects, List params) {
        List l = new ArrayList();
        objects.forEach(x -> {
            Map m = new HashMap();
            params.forEach(p -> m.put(p, x[params.indexOf(p)]));
            l.add(m);
        });
        return l;
    }

    public String toJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long unixTimeSeconds() {
        return System.currentTimeMillis() / 1000L;
    }
}