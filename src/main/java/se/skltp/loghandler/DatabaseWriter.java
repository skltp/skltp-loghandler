package se.skltp.loghandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.skltp.loghandler.models.Anslutning;
import se.skltp.loghandler.models.AnslutningDao;

import java.util.List;

/**
 * Created by parlin on 2017-10-02.
 */
@Component
public class DatabaseWriter {

    @Autowired
    LogpostParserService logpostParserService;

    @Autowired
    private AnslutningDao anslutningDao;

    @Scheduled(fixedDelay = 5000)
    public void handleAnslutningar() {
        System.out.println("Running handleAnslutningar");
        List<Anslutning> anslutningar = logpostParserService.getLatestAnlutningar();
        for (Anslutning a : anslutningar) {
            Anslutning anslutning = anslutningDao.getByExample(a);
            if(anslutning == null) {
                anslutning = a;
            } else {
                anslutning.setYoungest(a.getYoungest());
            }

            anslutningDao.update(anslutning);
        }
    }
}
