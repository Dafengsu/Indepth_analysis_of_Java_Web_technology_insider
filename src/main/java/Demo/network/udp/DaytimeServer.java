package Demo.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @description:
 * @author: su
 * @date: 2020/2/10
 */
public class DaytimeServer {
    private final static int PORT = 13;
    private final static Logger audit = Logger.getLogger("request");
    private final static Logger error = Logger.getLogger("error");

    public static void main(String[] args) {
        try (DatagramSocket server = new DatagramSocket(13)) {
            while (true) {
                try {
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                    server.receive(request);
                    String daytime = new Date().toString();
                    byte[] data = daytime.getBytes(StandardCharsets.US_ASCII);
                    DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
                    server.send(response);
                    audit.info(daytime + " " + request.getAddress());
                } catch (IOException | RuntimeException e) {
                    error.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        } catch (SocketException e) {
            error.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
