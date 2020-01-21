package Demo.IO.nio2.asyn.server;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/20 15:09
 */
public class ConnectionHandler implements CompletionHandler<AsynchronousSocketChannel,Attachment> {
    @Override
    public void completed(AsynchronousSocketChannel channelClient, Attachment att) {
        try {
            SocketAddress clientAddr = channelClient.getRemoteAddress();
            System.out.println("Accepted connection from " + clientAddr);

            att.channelServer.accept(att, this);

            Attachment newAtt = new Attachment();
            newAtt.channelServer = att.channelServer;
            newAtt.channelClient = channelClient;
            newAtt.isReadMode = true;
            newAtt.buffer = ByteBuffer.allocate(2018);
            newAtt.clientAddr = clientAddr;
            ReadWriteHandler rwh = new ReadWriteHandler();
            channelClient.read(newAtt.buffer, newAtt, rwh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {

    }
}
