package se.skltp.loghandler;

import org.apache.log4j.spi.LoggingEvent;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
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
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.*;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
public class Application {

  public Application() {

  }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }

    @Bean(name = "logpostParserPool")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("LogpostParser-");
        executor.initialize();
        return executor;
    }

    @Bean
    public TcpNetServerConnectionFactory cf() {
        TcpNetServerConnectionFactory factory = new TcpNetServerConnectionFactory(9999);
        //ByteArrayCrLfSerializer des = (ByteArrayCrLfSerializer)factory.getDeserializer();
        factory.setDeserializer(new MyDeserializer());
        //des.setMaxMessageSize(800000);
        return factory;
    }

    @Bean
    public TcpReceivingChannelAdapter inbound(AbstractServerConnectionFactory cf) {
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(cf);
        adapter.setOutputChannel(tcpIn());
        return adapter;
    }

    @Bean
    public MessageChannel tcpIn() {
        return new DirectChannel();
    }

    @Transformer(inputChannel = "tcpIn", outputChannel = "serviceChannel")
    @Bean
    public ObjectToStringTransformer transformer() {
        return new ObjectToStringTransformer();
    }

    @ServiceActivator(inputChannel = "serviceChannel")
    public void service(String in) {
        //System.out.println(in);
        LogpostHandler.instance.addLogpost(in);
        System.out.println("Added logpost");
    }

    private class MyDeserializer implements Deserializer<String> {
        @Override
        public String deserialize(InputStream inputStream) throws IOException {
            //ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(inputStream));
            //LoggingEvent event = (LoggingEvent) ois.readObject();
            //return event.toString();

            //Kod för test via telnet
            StringBuilder strBuilder = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            do {
                line = in.readLine();
                if(strBuilder.length() > 0) {
                    strBuilder.append('\n');
                }
                strBuilder.append(line);
                //System.out.println(line);
            } while (!line.startsWith("** logEvent-debug.end"));

            return strBuilder.toString();
        }
    }

}
