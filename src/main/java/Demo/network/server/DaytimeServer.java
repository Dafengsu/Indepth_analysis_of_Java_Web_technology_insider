package Demo.network.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @description:
 * @author: su
 * @date: 2020/2/6
 */
public class DaytimeServer {
    public static final int PORT = 13;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.US_ASCII);
                    out.write(new Date().toString());
                    out.flush();
                } catch (IOException ignored) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
