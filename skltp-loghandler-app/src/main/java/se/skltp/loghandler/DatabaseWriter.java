package se.skltp.loghandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.skltp.loghandler.models.entity.Anslutning;
import se.skltp.loghandler.models.dao.AnslutningDao;

import java.util.*;

/**
 * Created by parlin on 2017-10-02.
 */
@Component
public class DatabaseWriter {

    @Autowired
    private AnslutningDao anslutningDao;

    @Scheduled(fixedDelay = 5000)
    public void handleAnslutningar() {
        List<Anslutning> anslutningar = LogpostParserService.getLatestAnlutningar();
        Set<Anslutning> anslutningarSet = new HashSet<>();
        for (Anslutning a : anslutningar) {
            anslutningarSet.add(a);
        }

        anslutningarSet.forEach(a -> {
            Anslutning anslutning = anslutningDao.getByExample(a);
            if(anslutning == null) {
                anslutning = a;
                anslutning.setOldest(anslutning.getYoungest());
            } else {
                anslutning.setYoungest(a.getYoungest());
            }

            anslutningDao.update(anslutning);
        });
    }
}
