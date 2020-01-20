package Demo.IO.nio2.asyn;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/20 14:17
 */
public class AFCDemo02 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java AFCDemo path");
            return;
        }
        Path path = Paths.get(args[0]);
        AsynchronousFileChannel ch = AsynchronousFileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Thread mainThd = Thread.currentThread();
        ch.read(buffer, 0, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("Bytes read = " + result);
                mainThd.interrupt();
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("Failure: " + exc.toString());
                mainThd.interrupt();
            }
        });
        System.out.println("Waiting for completion");
        try
        {
            mainThd.join();
        }
        catch (InterruptedException ie)
        {
            System.out.println("Terminating");
        }
        ch.close();
    }
}
