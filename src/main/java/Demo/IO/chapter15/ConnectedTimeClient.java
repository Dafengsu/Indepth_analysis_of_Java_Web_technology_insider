package Demo.IO.chapter15;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.util.Date;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/13 15:27
 */
public class ConnectedTimeClient {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        InetSocketAddress address = new InetSocketAddress(0);
        channel.bind(address);
        InetSocketAddress server = new InetSocketAddress("time.nist.gov", 37);
        channel.connect(server);
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putLong((byte) 0);
        buffer.flip();
        channel.write(buffer);
        buffer.clear();
        buffer.putInt(0);
        channel.read(buffer);
        buffer.flip();
        long secondsSince1900 = buffer.getLong( );
        long differenceBetweenEpochs = 2208988800L;
        long secondsSince1970
                = secondsSince1900 - differenceBetweenEpochs;
        long msSince1970 = secondsSince1970 * 1000;
        Date time = new Date(msSince1970);
        System.out.println(time);
    }
}
