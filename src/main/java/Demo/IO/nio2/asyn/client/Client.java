package Demo.IO.nio2.asyn.client;




import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/21 13:05
 */
public class Client {
    private final static Charset CSUTF8 = StandardCharsets.UTF_8;

    private final static String HOST = "localhost";

    private final static int POST = 9090;

    public static void main(String[] args) {
        AsynchronousSocketChannel channel;
        try {
            channel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            System.err.println("Unable to open client socket channel");
            return;
        }

        try {
            channel.connect(new InetSocketAddress(HOST, POST)).get();
            System.out.println("Client at " + channel.getLocalAddress() + " connected");
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Server not responding");
            return;
        } catch (IOException e) {
            System.err.println("Unable to obtain client socket channel's local address");
            return;
        }

        Attachment att = new Attachment();
        att.channel = channel;
        att.isReadMode = false;
        att.buffer = ByteBuffer.allocate(2048);
        att.mainThd = Thread.currentThread();

        byte[] data = "Hello".getBytes(CSUTF8);
        att.buffer.put(data);
        att.buffer.flip();
        channel.write(att.buffer, att, new ReadWriteHandler());

        try {
            att.mainThd.join();
        } catch (InterruptedException e) {
            System.out.println("Client terminating");
        }
    }
}
