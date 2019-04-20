package business;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Util {

    public static Date localDateToDate(LocalDate d){
        return Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
