package Demo.network.udp;




import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: su
 * @date: 2020/2/10
 */
public class UDPDiscardServer {
    public static final int PORT = 9;
    public static final int MAX_PACKET_SIZE = 66507;

    public static void main(String[] args) {
        byte[] buffer = new byte[MAX_PACKET_SIZE];
        try (DatagramSocket server = new DatagramSocket(PORT)) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                try {
                    server.receive(packet);
                    String s = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
                    System.out.println(packet.getAddress() + " at port " + packet.getPort() + " says " + s);
                    packet.setLength(buffer.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
