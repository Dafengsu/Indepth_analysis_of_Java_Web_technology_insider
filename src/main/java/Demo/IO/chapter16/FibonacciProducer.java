package Demo.IO.chapter16;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.WritableByteChannel;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/13 19:24
 */
public class FibonacciProducer extends Thread{
    private WritableByteChannel out;
    private int howMany;
    public FibonacciProducer(WritableByteChannel out, int howMany) {
        this.out = out;
        this.howMany = howMany;
    }

    @Override
    public void run() {
        BigInteger low = BigInteger.ONE;
        BigInteger high = BigInteger.ONE;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.putInt(this.howMany);
            buffer.flip();
            while (buffer.hasRemaining()) {
                out.write(buffer);
            }
            for (int i = 0; i < howMany; i++) {
                byte[] data = low.toByteArray();
                buffer = ByteBuffer.allocate(4 + data.length);
                buffer.putInt(data.length);
                buffer.put(data);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    out.write(buffer);
                }
                BigInteger temp = high;
                high = high.add(low);
                low = temp;
            }
            out.close();
            System.err.println("Closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
