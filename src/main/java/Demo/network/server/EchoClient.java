package Demo.network.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: su
 * @date: 2020/2/11
 */
public class EchoClient {
    public static final int DEFAULT_PORT = 7;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost",DEFAULT_PORT)) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.UTF_8));
            writer.write("测试");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
