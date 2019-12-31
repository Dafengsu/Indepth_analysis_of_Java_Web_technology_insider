package Demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        ServerSocket socket = serverSocketChannel.socket();
        serverSocketChannel.bind(new InetSocketAddress(11212));
        ServerSocket socket = serverSocketChannel.socket();
    }
}
