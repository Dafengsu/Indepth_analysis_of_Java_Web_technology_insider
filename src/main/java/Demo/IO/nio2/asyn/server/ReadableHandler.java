package Demo.IO.nio2.asyn.server;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/20 15:23
 */
public class ReadableHandler implements CompletionHandler<Integer, Attachment> {

    @Override
    public void completed(Integer result, Attachment att) {
        if (result == -1) {
            try {
                att.channelClient.close();
                System.out.println("Stopped listening to client " + att.clientAddr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if (att.isReadMode) {
            att.buffer.flip();
            int limit = att.buffer.limit();
            byte[] bytes = new byte[limit];
            att.buffer.get(bytes, 0, limit);
            System.out.println("Client at " + att.clientAddr + " send message: " + new String(bytes));
            att.isReadMode = false;

            att.buffer.rewind();
            att.channelClient.write(att.buffer, att, this);
        } else {
            att.isReadMode = true;
            att.buffer.clear();
            att.channelClient.read(att.buffer, att, this);
        }
    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        System.out.println("Connection with client broken");
    }
}
