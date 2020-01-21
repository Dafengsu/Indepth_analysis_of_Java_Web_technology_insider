package Demo.IO.nio2.asyn.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/20 14:57
 */
public class Server {
    private static final int PORT = 9090;

    private final static String HOST = "localhost";

    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannel channelServer;

        try {
            channelServer = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(HOST, PORT));
            System.out.println("Server listening at " + channelServer.getLocalAddress());
        } catch (IOException e) {
            System.err.println("Unable to open or bind server socket channel");
            return;
        }
        Attachment att = new Attachment();
        att.channelServer = channelServer;
        channelServer.accept(att, new ConnectionHandler());

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.out.println("Server terminating");
        }
    }
}
