package se.skltp.loghandler.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by parlin on 2017-10-10.
 */
@Configuration
@EnableAsync
public class LogparserPoolConfig {

    @Autowired
    private Environment env;

    @Bean(name = "logpostParserPool")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Integer.parseInt(env.getProperty("threadpool.corepoolsize")));
        executor.setMaxPoolSize(Integer.parseInt(env.getProperty("threadpool.maxpoolsize")));
        executor.setQueueCapacity(Integer.parseInt(env.getProperty("threadpool.queuecapacity")));
        executor.setThreadNamePrefix("LogpostParser-");
        executor.initialize();
        return executor;
    }
}
