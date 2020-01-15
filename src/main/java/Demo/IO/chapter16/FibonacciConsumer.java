package Demo.IO.chapter16;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/13 19:32
 */
public class FibonacciConsumer extends Thread{
    private ReadableByteChannel in;

    public FibonacciConsumer(ReadableByteChannel in) {
        this.in = in;
    }

    @Override
    public void run() {
        ByteBuffer sizeb = ByteBuffer.allocate(4);
        try {
            while (sizeb.hasRemaining()) {
                in.read(sizeb);
            }
            sizeb.flip();
            int howMany = sizeb.getInt();
            sizeb.clear();
            for (int i = 0; i < howMany; i++) {
                while (sizeb.hasRemaining()) {
                    in.read(sizeb);
                }
                sizeb.flip();
                int length = sizeb.getInt();
                sizeb.clear();
                ByteBuffer data = ByteBuffer.allocate(length);
                while (data.hasRemaining()) {
                    in.read(data);
                }
                BigInteger result = new BigInteger(data.array());
                System.err.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException ignored) {

            }
        }
    }
}
