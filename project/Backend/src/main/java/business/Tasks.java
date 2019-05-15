package business;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class Tasks {

    @PostConstruct
    public void startUp() {
        data.Util.createViews();
        data.Util.refreshViews();
    }

    //every day 3 am
    @Scheduled(cron = "0 0 3 * * ?")
    public void refreshViews() {
        data.Util.refreshViews();
    }

    //@Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "* * * ? * *")
    public void downloadAndPopulateInfo() throws Exception {
        //TODO: run python download and populate script
    }
}
