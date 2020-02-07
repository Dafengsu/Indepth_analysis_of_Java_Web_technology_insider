package Demo.network.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @description:
 * @author: su
 * @date: 2020/2/6
 */
public class MultithreadedDaytimeServer {
    public static final int PORT = 13;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket connection = server.accept();
                    new Thread(()->{
                        doDaytimeTask(connection);
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void doDaytimeTask(Socket connection) {
        try {
            Writer out = new OutputStreamWriter(connection.getOutputStream());
            out.write(new Date().toString() + "\r\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
