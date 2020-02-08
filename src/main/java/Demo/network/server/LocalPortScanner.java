package Demo.network.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @description:
 * @author: su
 * @date: 2020/2/7
 */
public class LocalPortScanner {
    public static void main(String[] args) {
        for (int i = 0; i < 65535; i++) {
            try {
                ServerSocket server = new ServerSocket(i);
            } catch (IOException e) {
                System.out.println("There is a server on port " + i);
            }
        }
    }
}
