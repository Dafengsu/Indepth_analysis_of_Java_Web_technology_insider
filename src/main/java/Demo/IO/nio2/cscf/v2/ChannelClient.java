package Demo.IO.nio2.cscf.v2;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/21 15:31
 */
public class ChannelClient {
    final static int PORT = 9999;

    public static void main(String[] args) throws IOException {
        NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        DatagramChannel dc = DatagramChannel.open(StandardProtocolFamily.INET)
                .setOption(StandardSocketOptions.SO_REUSEADDR, true)
                .bind(new InetSocketAddress(PORT))
                .setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
        InetAddress group = InetAddress.getByName("239.255.0.1");
        MembershipKey key = dc.join(group, ni);

        ByteBuffer response = ByteBuffer.allocate(50);
        while (true) {
            dc.receive(response);
            response.flip();
            while (response.hasRemaining()) {
                System.out.print((char) response.get());
            }
            System.out.println();
            response.clear();
        }
    }
}
