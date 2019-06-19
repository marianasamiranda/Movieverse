package business;

import java.util.Map;

public interface AdminService {
    Map plots(String token, Integer time) throws Exception;

    Map stats(String token, Integer time) throws Exception;
}
