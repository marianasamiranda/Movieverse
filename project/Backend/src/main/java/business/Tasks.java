package business;

import data.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Tasks {

    @Autowired
    private DataUtil util;

    @PostConstruct
    public void startUp() throws IOException {
        util.createViews();
        util.refreshViews(true);
    }

    //every day 1 am
    @Scheduled(cron = "0 0 1 * * ?")
    public void refreshViews() {
        util.refreshViews(false);
    }

    //@Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "* * * ? * *")
    public void downloadAndPopulateInfo() throws Exception {
        //TODO: run python download and populate script
    }
}
