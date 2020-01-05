package Demo.IO.chapter05;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 19:24
 */
public class HelloServer {
    public static void main(String[] args) throws IOException {
        int port = 2345;
        ServerSocket ss = new ServerSocket(port);
        while (true) {
            try {
                Socket s = ss.accept();
                StringBuilder response = new StringBuilder("Hello " + s.getInetAddress() + " on port " + s.getPort() + " \r\n");
                response.append("This is ").append(s.getLocalAddress()).append(" on port ").append(s.getLocalPort()).append(" \r\n");
                OutputStream out = s.getOutputStream();
                out.write(response.toString().getBytes(StandardCharsets.US_ASCII));
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
