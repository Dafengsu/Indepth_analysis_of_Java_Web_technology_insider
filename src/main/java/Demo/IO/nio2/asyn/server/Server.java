package Demo.IO.nio2.asyn.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        AsynchronousChannelGroup pool = AsynchronousChannelGroup.withThreadPool(executorService);
//      AsynchronousChannelGroup pool = AsynchronousChannelGroup.withFixedThreadPool(4,Executors.defaultThreadFactory());
//        AsynchronousChannelGroup pool = AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 4);
        try {
            channelServer = AsynchronousServerSocketChannel.open(pool).bind(new InetSocketAddress(HOST, PORT));
            System.out.println("Server listening at " + channelServer.getLocalAddress());
        } catch (IOException e) {
            System.err.println("Unable to open or bind server socket channel");
            return;
        }
       /* executorService.execute(()->{
            Thread thread = Thread.currentThread();
            System.out.println("Thread is daemon: " + thread.isDaemon());
        });*/
        Attachment att = new Attachment();
        att.channelServer = channelServer;
      /*  new Thread().start();*/
        channelServer.accept(att, new ConnectionHandler());

        try {
            Thread.currentThread().join(5000);
        } catch (InterruptedException e) {
            System.out.println("Server terminating");
        }
    }
}
