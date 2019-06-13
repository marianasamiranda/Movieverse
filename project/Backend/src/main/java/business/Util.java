
package business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public String toJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object fromJson(String json, Class c) {
        try {
            return objectMapper.readValue(json, c);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long unixTimeSeconds() {
        return System.currentTimeMillis() / 1000L;
    }

    public Date localDateTimeToDateTime(LocalDateTime t) {
        return Date.from(t.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date parseDate(String stringDate) {
        var date = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
            date = sdf.parse(stringDate);
        }
        catch(ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public String formatDate(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(d);
    }
}