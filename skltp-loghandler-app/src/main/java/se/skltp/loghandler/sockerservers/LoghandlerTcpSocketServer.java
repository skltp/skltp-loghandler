package se.skltp.loghandler.sockerservers;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.net.server.ObjectInputStreamLogEventBridge;
import org.apache.logging.log4j.core.net.server.TcpSocketServer;
import se.skltp.loghandler.LogpostHandler;

import java.io.IOException;

/**
 * Created by parlin on 2017-10-17.
 */
public class LoghandlerTcpSocketServer extends TcpSocketServer {

    public LoghandlerTcpSocketServer(int port) throws IOException {
        super(port, new ObjectInputStreamLogEventBridge());
    }

    public void log(final LogEvent event) {
        LogpostHandler.instance.addLogpost(event.getMessage().getFormattedMessage());
    }
}
