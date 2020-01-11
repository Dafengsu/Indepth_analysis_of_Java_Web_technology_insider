package Demo.IO.chapter14;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/11 10:32
 */
public class RootsChannel {
    final static int SIZE_OF_DOUBLE = 8;
    final static int LENGTH = 1001;

    public static void main(String[] args) throws IOException {
        ByteBuffer data = ByteBuffer.allocateDirect(SIZE_OF_DOUBLE * LENGTH);
        DoubleBuffer roots = data.asDoubleBuffer();
        while (roots.hasRemaining()) {
            roots.put(Math.sqrt(roots.position()));
        }
        FileOutputStream fout = new FileOutputStream("src/main/resources/roots.dat");
        FileChannel outChannel = fout.getChannel();
        outChannel.write(data);
        outChannel.close();
    }
}
