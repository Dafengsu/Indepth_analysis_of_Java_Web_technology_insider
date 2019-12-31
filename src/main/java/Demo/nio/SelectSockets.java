package Demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;

public class SelectSockets {
    public static int PORT_NUMBER = 1234;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    public static SelectionKey key;
    public static void main(String[] args) throws IOException {
        new SelectSockets().go(args);
    }

    protected void go(String[] args) throws IOException {
        int port = PORT_NUMBER;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        System.out.println("Listening on port " + port);

        ServerSocketChannel serverChannel = ServerSocketChannel.open().bind(new InetSocketAddress(port));
        Selector selector = Selector.open();
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int n = selector.select();
            if (n == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                /*SelectionKey */key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();

                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    sayHello(channel);
                }

                if (key.isReadable()) {
                    readDataFromSocket(key);
                }
                iterator.remove();
            }
        }
    }

    protected void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        buffer.clear();
        while ((count = socketChannel.read(buffer)) > 0) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
                System.out.println(buffer);
            }

        }

       if (count < 0) {
            socketChannel.close();
        }

    }

    private void sayHello(SocketChannel channel) throws IOException {
        buffer.clear();
        buffer.put("Hi there!\r\n".getBytes());
        buffer.flip();

        channel.write(buffer);
    }

    protected void registerChannel(Selector selector, SocketChannel channel, int ops) throws IOException {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }
}
