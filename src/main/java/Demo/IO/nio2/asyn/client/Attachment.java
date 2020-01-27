package Demo.IO.nio2.asyn.client;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/21 13:13
 */
public class Attachment {
    public AsynchronousSocketChannel channel;
    public boolean isReadMode;
    public ByteBuffer buffer;
    public Thread mainThd;
}
