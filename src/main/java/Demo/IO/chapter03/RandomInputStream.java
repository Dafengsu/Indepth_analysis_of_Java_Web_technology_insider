package Demo.IO.chapter03;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 11:17
 */
public class RandomInputStream extends InputStream {
    private Random generator = new Random();
    private boolean closed = false;

    @Override
    public int read() throws IOException {
        checkOpen();
        int result = generator.nextInt() % 256;
        if (result < 0) {
            result = -result;
        }
        return result;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        checkOpen();
        byte[] temp = new byte[len];
        generator.nextBytes(temp);
        System.arraycopy(temp, 0, b, off, len);
        return len;
    }

    @Override
    public int read(byte[] b) throws IOException {
        checkOpen();
        generator.nextBytes(b);
        return b.length;
    }

    @Override
    public long skip(long n) throws IOException {
        checkOpen();
        return n;
    }

    @Override
    public void close() {
        this.closed = true;
    }

    @Override
    public int available() {
        return Integer.MAX_VALUE;
    }

    private void checkOpen() throws IOException {
        if (closed) {
            throw new IOException("Input stream closed");
        }
    }
}
