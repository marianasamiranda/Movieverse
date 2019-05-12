package data.daos;

import data.entities.Member;

import java.util.Date;
import java.util.List;


public interface MemberDAO extends DAO<Integer , Member> {
    List bornToday(int begin, int limit);
    List mostCredits(int begin, int limit);
}
