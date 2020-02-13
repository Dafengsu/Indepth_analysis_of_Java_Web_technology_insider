package Demo.network.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: su
 * @date: 2020/2/10
 */
public class UDPEchoClientWithChannels {
    public static final int PORT = 7;
    private final static int LIMIT = 100;

    public static void main(String[] args) {
        SocketAddress remote;
        try {
            remote = new InetSocketAddress(args[0], PORT);
        } catch (RuntimeException ex) {
            System.err.println("Usage: java UDPEchoClientWithChannels host");
            return;
        }

        try (DatagramChannel channel = DatagramChannel.open()) {
            channel.configureBlocking(false);
            channel.connect(remote);
            Selector selector = Selector.open();

            channel.register(selector, SelectionKey.OP_READ
                    | SelectionKey.OP_WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(4);
            int n = 0;
            int numberRead = 0;
            while (true) {
                if (numberRead == LIMIT) break;
                selector.select(60000);
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                if (readyKeys.isEmpty() && n == LIMIT) {
                    break;
                } else {
                    Iterator<SelectionKey> iterator = readyKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            buffer.clear();
                            channel.read(buffer);
                            buffer.flip();
                            int echo = buffer.getInt();
                            System.out.println("Read: " + echo);
                            numberRead++;
                        }
                        if (key.isWritable()) {
                            buffer.clear();
                            buffer.putInt(n);
                            buffer.flip();
                            channel.write(buffer);
                            System.out.println("Wrote: " + n);
                            n++;
                            if (n == LIMIT) {
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                }

            }
            System.out.println("Echoed " + numberRead + " out of "
                    + LIMIT + " sent");
            System.out.println("Success rate: " + 100.0 * numberRead
                    / LIMIT + "%");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
