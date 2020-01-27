package Demo.IO.nio2.cscf.v2;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/21 15:23
 */
public class ChannelServer {
    final static int PORT = 9999;

    public static void main(String[] args) throws IOException {
        NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        DatagramChannel dc = DatagramChannel.open(StandardProtocolFamily.INET)
                .setOption(StandardSocketOptions.SO_REUSEADDR, true)
                .bind(new InetSocketAddress(PORT))
                .setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
        InetAddress group = InetAddress.getByName("239.255.0.1");

        int i = 0;
        while (true) {
            ByteBuffer bb = ByteBuffer.wrap(("line " + i).getBytes());
            dc.send(bb, new InetSocketAddress(group, PORT));
            i++;
        }
    }
}
