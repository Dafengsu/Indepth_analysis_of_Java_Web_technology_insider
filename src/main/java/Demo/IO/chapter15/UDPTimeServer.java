package Demo.IO.chapter15;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.util.Date;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/13 14:42
 */
public class UDPTimeServer {
    public static final int DEFAULT_PORT = 37;

    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[1]);
                if (port <= 0 || port > 65535) {
                    port = DEFAULT_PORT;
                }
            } catch (NumberFormatException ignored) {

            }
        }

        ByteBuffer in = ByteBuffer.allocateDirect(8192);
        ByteBuffer out = ByteBuffer.allocateDirect(8);
        out.order(ByteOrder.BIG_ENDIAN);
        InetSocketAddress address = new InetSocketAddress(port);
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(address);
        System.err.println("bound to " + address);
        while (true) {
            try {
                in.clear();
                SocketAddress client = channel.receive(in);
                System.err.println(client);
                long secondsSince1900 = getTime();
                out.clear();
                out.putLong(secondsSince1900);
                out.flip();
                out.position(4);
                channel.send(out, client);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private static long getTime() {
        long differenceBetweenEpochs = 2208988800L;
        Date now = new Date( );
        long secondsSince1970 = now.getTime( ) / 1000;
        return secondsSince1970 + differenceBetweenEpochs;
    }
}
