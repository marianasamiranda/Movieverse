package business;

import java.util.List;

public interface MemberService {
    Object memberInfo(int id);

    List search(String name) throws Exception;

    Object memberMovies(int id, int page);

    int estimatedCount();

    Object memberSearchPage();
}
