package Demo.network.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @description:
 * @author: su
 * @date: 2020/2/10
 */
public class UDPDiscardServerWithChannels {
    public final static int PORT = 9;
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        try {
            DatagramChannel channle = DatagramChannel.open();
            channle.bind(new InetSocketAddress(PORT));
            ByteBuffer buffer = ByteBuffer.allocate(MAX_PACKET_SIZE);
            while (true) {
                SocketAddress client = channle.receive(buffer);
                buffer.flip();
                System.out.println(client + " says ");
                while (buffer.hasRemaining())
                    System.out.write(buffer.get());
                System.out.println();
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
