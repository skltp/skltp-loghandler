package se.skltp.loghandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    private static final Logger logger = LogManager.getLogger(DatabaseWriter.class);

    @Autowired
    private AnslutningDao anslutningDao;

    @Scheduled(fixedDelay = 5000)
    public void handleAnslutningar() {
        List<Anslutning> anslutningar = LogpostParserService.getLatestAnlutningar();
        if(anslutningar.isEmpty()) {
            return;
        }
        if(logger.isDebugEnabled()) {
            logger.debug("DatabaseWriter hanterar anslutningar, antal: " + anslutningar.size());
        }
        Set<Anslutning> anslutningarSet = new HashSet<>();
        for (Anslutning a : anslutningar) {
            anslutningarSet.add(a);
        }

        saveAnslutningar(anslutningarSet);
    }

    private void saveAnslutningar(Set<Anslutning> anslutningarSet) {
        if(logger.isDebugEnabled()) {
            logger.debug("DatabaseWriter sparar anslutningar efter rensning av dubletter, antal: " + anslutningarSet.size());
        }
        anslutningarSet.forEach(a -> {
            try {
                Anslutning anslutning = anslutningDao.getByExample(a);
                if(anslutning == null) {
                    anslutning = a;
                    anslutning.setOldest(anslutning.getYoungest());
                } else {
                    anslutning.setYoungest(a.getYoungest());
                }

                anslutningDao.update(anslutning);
            } catch (DataIntegrityViolationException e) {
                logger.error("DatabaseWriter, det gick inte att spara anslutningen till databasen", e);
            }

        });
    }
}
