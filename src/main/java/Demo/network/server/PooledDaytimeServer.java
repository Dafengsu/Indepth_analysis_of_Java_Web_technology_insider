package Demo.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Demo.network.server.MultithreadedDaytimeServer.doDaytimeTask;

/**
 * @description:
 * @author: su
 * @date: 2020/2/6
 */
public class PooledDaytimeServer {
    public static final int PORT = 13;
    public static final int NUM_OF_POOL = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_POOL);
        try (ServerSocket server = new ServerSocket(PORT)) {
            try {
                Socket connection = server.accept();
                executor.execute(()->{
                    doDaytimeTask(connection);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
