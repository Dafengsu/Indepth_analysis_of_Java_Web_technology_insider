package Demo.network.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @description:
 * @author: su
 * @date: 2020/2/7
 */
public class RandomPort {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(0);
            System.out.println("This server runs on port " + server.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
