package Demo.IO.chapter15;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/13 13:31
 */
public class HTTPGrap {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java HTTPGrap url filename");
            return;
        }

        URL u = new URL(args[0]);
        if (!u.getProtocol().equals("http")) {
            System.err.println("Sorry, " + u.getProtocol() + " is not support");
            return;
        }
        String host = u.getHost();
        int port = u.getPort();
        String file = u.getFile();
        if (file == null) {
            file = "/";
        }
        if (port <= 0) {
            port = 80;
        }
        InetSocketAddress remote = new InetSocketAddress(host, port);
        SocketChannel channel = SocketChannel.open(remote);
        FileChannel localFile = new FileOutputStream(args[1]).getChannel();
        String request = "GET " + file + " HTTP/1.1\r\n"
                + "User-Agent: HTTPGrab\r\n"
                + "Accept: text/*\r\n"
                + "Connection: close\r\n"
                + "Host: " + host + "\r\n"
                + "\r\n";
        ByteBuffer header = ByteBuffer.wrap(request.getBytes("US-ASCII"));
        channel.write(header);
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        while (channel.read(buffer) != -1) {
            buffer.flip();
            localFile.write(buffer);
            buffer.clear();
        }
        localFile.close();
        channel.close();
    }
}
