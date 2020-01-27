package Demo.IO.nio2.cscf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/21 14:58
 */
public class ChannelServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting server...");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9999));
        ssc.configureBlocking(false);
        String msg = "Local address: " + ssc.getLocalAddress();
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        while (true) {
            System.out.print(".");
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                System.out.println();
                System.out.println("Received connection from " + sc.getRemoteAddress());

                buffer.rewind();
                sc.write(buffer);
                sc.close();
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    assert false;
                }
            }
        }
    }
}
