package business;

import data.DataUtil;
import data.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
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
    private DataUtil dataUtil;

    @Autowired
    private Util util;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MovieManager movieManager;


    @PostConstruct
    public void startUp() {
        dataUtil.createViews();
        dataUtil.refreshViews(true);
        clearRedisCache();
    }


    //every day 1 am
    @Scheduled(cron = EVERY_DAY_1AM)
    public void refreshViews() {
        dataUtil.refreshViews(false);
    }


    @Scheduled(cron = EVERY_5_MINUTES)
    public void clearRedisCache() {
        List<String> valuesToCheck = new ArrayList<>(Arrays.asList(
                "news", "frontPageInfo", "movieSearchPageInfo", "movieSearchPageInfo")
        );
        movieManager.theatersIds().forEach(x ->
            valuesToCheck.add("showtimes_" + x)
        );

        valuesToCheck.forEach(x -> {
            String timeS = redisCache.get(x + "_date");
            if (timeS != null && Long.parseLong(timeS) <= util.unixTimeSeconds())
                redisCache.del(x);
        });
    }


    //@Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "* * * ? * *")
    public void downloadAndPopulateInfo() throws Exception {
        //TODO: run python download and populate script
    }
}
