package Demo.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

/**
 * @description:
 * @author: su
 * @date: 2020/2/9
 */
public class IntgenClient {
    public static int DEFAULT_PORT = 1919;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java IntgenClient host [port]");
        }
        int port;
        try {
            port = Integer.parseInt(args[1]);
        } catch (RuntimeException e) {
            port = DEFAULT_PORT;
        }

        try {
            InetSocketAddress address = new InetSocketAddress(args[0], port);
            SocketChannel client = SocketChannel.open(address);
            ByteBuffer buffer = ByteBuffer.allocate(4);
            IntBuffer view = buffer.asIntBuffer();
            for (int expected = 0; ; expected++) {
                client.read(buffer);
                int actual = view.get();
                buffer.clear();
                view.rewind();

                if (actual != expected) {
                    System.err.println("Expected " + expected + "; was " + actual);
                    return;
                }
                System.out.println(actual);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
