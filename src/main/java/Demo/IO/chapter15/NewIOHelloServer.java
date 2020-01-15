package Demo.IO.chapter15;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/13 13:50
 */
public class NewIOHelloServer {
    public static final int PORT = 2345;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        InetSocketAddress port = new InetSocketAddress(PORT);
        serverChannel.bind(port);
        while (true) {
            try {
                SocketChannel clientChannel = serverChannel.accept();
                String response = "Hello "
                        + clientChannel.socket().getInetAddress() + " on port "
                        + clientChannel.socket().getPort() + "\r\n";
                response += "This is " + serverChannel.socket() + " on port "
                        + serverChannel.socket().getLocalPort() + "\r\n";
                byte[] data = response.getBytes(StandardCharsets.UTF_8);
                ByteBuffer buffer = ByteBuffer.wrap(data);
                while (buffer.hasRemaining()) {
                    clientChannel.write(buffer);
                }
                clientChannel.close();
            } catch (IOException ignored) {

            }
        }
    }
}
