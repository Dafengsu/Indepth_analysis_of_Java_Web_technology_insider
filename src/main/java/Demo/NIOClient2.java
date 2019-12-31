package Demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient2 {
    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

        while (true) {
            int select = selector.select();

            if (select > 0) {

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {

                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isConnectable()) {
                        System.err.println("Connectable");
                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                        clientChannel.finishConnect();
                        selectionKey.interestOps(SelectionKey.OP_READ);

                    } else if (selectionKey.isReadable()) {
                        System.out.println("Readable");
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(128);
                        channel.read(buffer);
                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                        System.out.println("收到服务端数据" + new String(buffer.array()));

                    } else if (selectionKey.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                        String str = "qiwoo mobile";
                        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
                        clientChannel.write(buffer);
                        selectionKey.interestOps(SelectionKey.OP_READ);
                        System.out.println("向服务端发送数据" + new String(buffer.array()));
                    }

                    iterator.remove();
                }
            }
        }
    }


}
