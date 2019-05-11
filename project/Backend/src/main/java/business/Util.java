
package business;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class Util {

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
}