package business;

import data.DataUtil;
import data.RedisCache;
import data.daos.AchievementDAO;
import data.daos.BadgeDAO;
import data.daos.MUserDAO;
import data.entities.Achievement;
import data.entities.Badge;
import data.entities.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Tasks {

    private static final String EVERY_MINUTE = "0 * * ? * *";
    private static final String EVERY_5_MINUTES = "0 */5 * ? * *";
    private static final String EVERY_HOUR = "0 0 * ? * *";
    private static final String EVERY_DAY_1AM = "0 0 1 * * ?";

    @Autowired
    private MUserDAO mUserDAO;

    @Autowired
    private AchievementDAO achievementDAO;

    @Autowired
    private BadgeDAO badgeDAO;

    @Autowired
    private DataUtil dataUtil;

    @Autowired
    private Util util;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MovieService movieService;


    @PostConstruct
    public void startUp() {
        try {
            //downloadAndPopulateInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataUtil.createViews();
        dataUtil.createFunctions();
        dataUtil.refreshViews();
        dataUtil.createTriggers();
        dataUtil.createIndexes();
        accountAgeAchievements();
        redisCache.deleteAll();
    }


    //every day 1 am
    @Scheduled(cron = EVERY_DAY_1AM)
    public void refreshViews() {
        dataUtil.refreshViews();
    }


    @Scheduled(cron = EVERY_5_MINUTES)
    public void clearRedisCache() {
        List<String> valuesToCheck = new ArrayList<>(Arrays.asList(
            "news", "frontPageInfo", "movieSearchPageInfo", "memberSearchPageInfo", "usersSearchPageInfo")
        );
        movieService.theatersIds().forEach(x ->
            valuesToCheck.add("showtimes_" + x)
        );

        valuesToCheck.forEach(x -> {
            String timeS = redisCache.get(x + "_date");
            if (timeS != null && Long.parseLong(timeS) <= util.unixTimeSeconds())
                redisCache.del(x);
        });
    }

    @Scheduled(cron = EVERY_DAY_1AM)
    @Transactional
    public void accountAgeAchievements() {
        List<String> badgesNameYears = Arrays.asList(
            "1 year old", "2 years old", "3 years old", "4 years old", "5 years old"
        );

        for (int years=1; years<=5; years++) {
            Badge badge = badgeDAO.getBadgeByName(badgesNameYears.get(years - 1));
            List<MUser> l = mUserDAO.nYearsWithoutBadge(years);
            l.forEach(x -> {
                Achievement achievement = new Achievement();
                achievement.setmUser(x);
                achievement.setBadge(badge);
                achievement.setDate(util.localDateToDate(LocalDate.now()));
                x.addBadge(achievement, badge);
                achievementDAO.persist(achievement);
                mUserDAO.merge(x);
            });
        }
    }


    //@Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = EVERY_DAY_1AM)
    public void downloadAndPopulateInfo() throws Exception {
        String path = new File("").getCanonicalPath();
        Runtime.getRuntime().exec("python " + path +"/scripts/populate_badges.py" );
        Runtime.getRuntime().exec("python " + path +"/scripts/populate_countries.py" );
        Runtime.getRuntime().exec("python " + path +"/scripts/populate_genres.py" );
        Runtime.getRuntime().exec("python " + path +"/scripts/populate_companies.py" );
        Runtime.getRuntime().exec("python " + path +"/scripts/populate_people.py" );
        Runtime.getRuntime().exec("python " + path +"/scripts/populate_movies.py" );

    }
}
