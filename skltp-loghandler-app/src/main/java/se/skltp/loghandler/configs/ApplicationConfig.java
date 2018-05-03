package se.skltp.loghandler.configs;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by parlin on 2017-10-10.
 */
@Configuration
@EnableAsync
@EnableCaching
@EnableScheduling
public class ApplicationConfig {


}
