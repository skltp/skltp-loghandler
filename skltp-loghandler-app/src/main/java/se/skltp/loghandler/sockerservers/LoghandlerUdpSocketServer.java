package se.skltp.loghandler.sockerservers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.net.server.ObjectInputStreamLogEventBridge;
import org.apache.logging.log4j.core.net.server.UdpSocketServer;
import se.skltp.loghandler.LogpostHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by parlin on 2017-10-17.
 */
public class LoghandlerUdpSocketServer extends UdpSocketServer {

    private static final Logger localLogger = LogManager.getLogger(LoghandlerTcpSocketServer.class);

    public LoghandlerUdpSocketServer(int port) throws IOException {
        super(port, new ObjectInputStreamLogEventBridge(){
            @Override
            public ObjectInputStream wrapStream(final InputStream inputStream) throws IOException {
                if(localLogger.isDebugEnabled()) {
                    localLogger.debug("Wrapping incoming stream as ObjectInputStream.");
                }
                return new ObjectInputStream(inputStream);
            }
        });
    }

    public void log(final LogEvent event) {
        LogpostHandler.instance.addLogpost(event.getMessage().getFormattedMessage());
    }
}
