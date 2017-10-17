package se.skltp.loghandler.configs;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.net.server.ObjectInputStreamLogEventBridge;
import org.apache.logging.log4j.core.net.server.TcpSocketServer;
import org.apache.logging.log4j.core.net.server.UdpSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import se.skltp.loghandler.LogpostHandler;
import se.skltp.loghandler.sockerservers.LoghandlerTcpSocketServer;
import se.skltp.loghandler.sockerservers.LoghandlerUdpSocketServer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.ObjectInputStream;

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
        return new LoghandlerTcpSocketServer(Integer.parseInt(env.getProperty("tcpsocket.port")));
    }

    @Bean
    public UdpSocketServer<ObjectInputStream> udpSocketServer() throws IOException {
        return new LoghandlerUdpSocketServer(Integer.parseInt(env.getProperty("udpsocket.port")));
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
