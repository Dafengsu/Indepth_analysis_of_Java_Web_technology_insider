package Demo.IO.nio2.asyn;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/20 14:10
 */
public class AFCDemo {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        if (args.length != 1) {
            System.err.println("usage: java AFCDemo path");
            return;
        }
        Path path = Paths.get(args[0]);
        AsynchronousFileChannel ch = AsynchronousFileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> result = ch.read(buffer, 0);
        while (!result.isDone()) {
            System.out.println("Sleeping");
            Thread.sleep(500);
        }
        System.out.println("Finished = " + result.isDone());
        System.out.println("Bytes read = " + result.get());
        ch.close();
    }
}
