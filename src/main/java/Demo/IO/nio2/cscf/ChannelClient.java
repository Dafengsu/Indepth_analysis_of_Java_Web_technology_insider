package Demo.IO.nio2.cscf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/21 15:02
 */
public class ChannelClient {
    public static void main(String[] args) {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.supportedOptions().forEach(System.out::println);
            System.out.println(sc.getOption(StandardSocketOptions.SO_RCVBUF));
            sc.configureBlocking(false);
            InetSocketAddress addr = new InetSocketAddress("localhost", 9999);
            sc.connect(addr);

            while (!sc.finishConnect()) {
                System.out.println("waiting to finish connection");
            }

            ByteBuffer buffer = ByteBuffer.allocate(2048);
            while (sc.read(buffer) >= 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                    buffer.clear();
                }
            }
            sc.close();
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
