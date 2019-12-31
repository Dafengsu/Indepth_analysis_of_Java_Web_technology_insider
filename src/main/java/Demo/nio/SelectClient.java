package Demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

public class SelectClient {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 1234));
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
            buffer.clear();
            int count;
            StringBuffer sb = new StringBuffer();
            while (sb.toString().equals("")) {
                while ((count = socketChannel.read(buffer)) > 0) {
                    sb.append(new String(buffer.array(), 0, count));
                }
            }
            System.out.println(sb.toString());
        }

    }
}
