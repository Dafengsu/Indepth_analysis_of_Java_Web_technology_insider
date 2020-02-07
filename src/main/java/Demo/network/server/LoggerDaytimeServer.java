package Demo.network.server;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static Demo.network.server.MultithreadedDaytimeServer.doDaytimeTask;

/**
 * @description:
 * @author: su
 * @date: 2020/2/6
 */
public class LoggerDaytimeServer {
    public static final int PORT = 13;
    public static final int NUM_OF_POOL = Runtime.getRuntime().availableProcessors();
    public static final Logger auditLogger = Logger.getLogger("request");
    public static final Logger errorLogger = Logger.getLogger("errors");
    public static final LogManager logManager = LogManager.getLogManager();
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_POOL);
        try (ServerSocket server = new ServerSocket(PORT)) {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/main/resources/log.properties"));
            logManager.readConfiguration(in);
            while (true) {
                try {
                    Socket connection = server.accept();
                    executor.execute(() -> {
                        auditLogger.info(new Date() + " " + connection.getRemoteSocketAddress());
                        doDaytimeTask(connection);
                    });
                } catch (IOException e) {
                    errorLogger.log(Level.SEVERE, "accept error", e);
                } catch (RuntimeException e) {
                    errorLogger.log(Level.SEVERE, "unexpected error", e);
                }

            }
        } catch (IOException e) {
            errorLogger.log(Level.SEVERE, "Couldn't start server", e);
        } catch (RuntimeException e) {
            errorLogger.log(Level.SEVERE, "Couldn't start server: " + e.getMessage(), e);
        }
    }

}
