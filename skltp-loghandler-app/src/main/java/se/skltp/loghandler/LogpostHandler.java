package se.skltp.loghandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by parlin on 2017-09-27.
 */
@Controller
public class LogpostHandler {

    @Autowired
    LogpostParserService logpostParserService;

    public static LogpostHandler instance;

    public LogpostHandler() {
        instance = this;
    }

    public void addLogpost(String logPost) {
        logpostParserService.parseLogpost(logPost);
        System.out.println("Parsed Logpost");
    }
}
