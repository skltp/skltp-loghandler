package se.skltp.loghandler;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import se.skltp.loghandler.models.entity.Anslutning;

import java.util.List;
import java.util.Date;

/**
 * Created by parlin on 2017-10-06.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource("classpath:application.properties")
public class LogpostParserServiceTest {

    @Autowired
    private LogpostParserService logpostParserService;

    @Test
    public void testOfullstandigLogpost() throws InterruptedException {
        List<Anslutning> list = logpostParserService.parseAndGetAnslutningar(new Date(), "Test");
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testParseLogpost() throws InterruptedException {
        List<Anslutning> list = logpostParserService.parseAndGetAnslutningar(new Date(), LogpostMocker.getMockLogpost());
        Assert.assertEquals(2, list.size());
    }


}