package se.skltp.loghandler;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.skltp.loghandler.models.Anslutning;

import java.util.List;

/**
 * Created by parlin on 2017-10-06.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class LogpostParserServiceTest {

    @Autowired
    private LogpostParserService logpostParserService;

    @Test
    public void testOfullstandigLogpost() throws InterruptedException {
        logpostParserService.parseLogpost("Test");
        List<Anslutning> latestAnlutningar = LogpostParserService.getLatestAnlutningar();
        Assert.assertEquals(0, latestAnlutningar.size());
    }

    @Test
    public void testParseLogpost() throws InterruptedException {
        logpostParserService.parseLogpost(LogpostMocker.getMockLogpost());
        Thread.sleep(1000);
        List<Anslutning> latestAnlutningar = LogpostParserService.getLatestAnlutningar();
        Assert.assertEquals(1, latestAnlutningar.size());
    }


}