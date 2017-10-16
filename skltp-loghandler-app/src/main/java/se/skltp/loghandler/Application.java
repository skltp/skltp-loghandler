package se.skltp.loghandler;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.net.server.ObjectInputStreamLogEventBridge;
import org.apache.logging.log4j.core.net.server.TcpSocketServer;
import org.apache.logging.log4j.core.net.server.UdpSocketServer;
import org.slf4j.event.LoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.serializer.Deserializer;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.messaging.MessageChannel;

import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.io.*;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class Application {

    public Application() {

    }

    @Autowired
    TcpSocketServer<ObjectInputStream> tcpSocketServer;

    @Autowired
    UdpSocketServer<ObjectInputStream> udpSocketServer;

    @Bean
    public TcpSocketServer<ObjectInputStream> tcpSocketServer() throws IOException {
        return new TcpSocketServer(9999, new ObjectInputStreamLogEventBridge()){
            public void log(final LogEvent event) {
                LogpostHandler.instance.addLogpost(event.getMessage().toString());
            }
        };
    }

    @Bean
    public UdpSocketServer<ObjectInputStream> udpSocketServer() throws IOException {
        return new UdpSocketServer(8888, new ObjectInputStreamLogEventBridge()){
            public void log(final LogEvent event) {
                LogpostHandler.instance.addLogpost(event.getMessage().toString());
            }
        };
    }

    @PostConstruct
    public void init() {
        final Thread tcpThread = new Thread(tcpSocketServer);
        tcpThread.start();
        final Thread udpThread = new Thread(udpSocketServer);
        udpThread.start();
    }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }
}
