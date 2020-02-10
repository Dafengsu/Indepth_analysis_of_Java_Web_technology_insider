package Demo.network.udp;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @description:
 * @author: su
 * @date: 2020/2/10
 */
public class UDPPortScanner {
    public static void main(String[] args) {
        for (int port = 1024; port < 65535; port++) {
            try {
                DatagramSocket server = new DatagramSocket(port);
                server.close();
            } catch (SocketException e) {
                System.out.println("There is a server on port " + port + ".");
            }
        }
    }
}
