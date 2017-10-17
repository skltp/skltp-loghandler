package se.skltp.loghandler.configs;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.net.server.ObjectInputStreamLogEventBridge;
import org.apache.logging.log4j.core.net.server.TcpSocketServer;
import org.apache.logging.log4j.core.net.server.UdpSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import se.skltp.loghandler.LogpostHandler;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.Executor;

/**
 * Created by parlin on 2017-10-10.
 */
@Configuration
public class SocketServerConfig {

    @Autowired
    private Environment env;

    @Autowired
    TcpSocketServer<ObjectInputStream> tcpSocketServer;

    @Autowired
    UdpSocketServer<ObjectInputStream> udpSocketServer;

    @Bean
    public TcpSocketServer<ObjectInputStream> tcpSocketServer() throws IOException {
        return new TcpSocketServer(Integer.parseInt(env.getProperty("tcpsocket.port")), new ObjectInputStreamLogEventBridge()){
            public void log(final LogEvent event) {
                LogpostHandler.instance.addLogpost(event.getMessage().getFormattedMessage());
            }
        };
    }

    @Bean
    public UdpSocketServer<ObjectInputStream> udpSocketServer() throws IOException {
        return new UdpSocketServer(Integer.parseInt(env.getProperty("udpsocket.port")), new ObjectInputStreamLogEventBridge()){
            public void log(final LogEvent event) {
                LogpostHandler.instance.addLogpost(event.getMessage().getFormattedMessage());
            }
        };
    }

    @PostConstruct
    public void init() {
        if(Boolean.parseBoolean(env.getProperty("tcpsocket.active"))) {
            final Thread tcpThread = new Thread(tcpSocketServer);
            tcpThread.start();
        }
        if(Boolean.parseBoolean(env.getProperty("udpsocket.active"))) {
            final Thread udpThread = new Thread(udpSocketServer);
            udpThread.start();
        }
    }
}
