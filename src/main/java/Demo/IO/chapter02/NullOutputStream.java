package Demo.IO.chapter02;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 10:24
 */
public class NullOutputStream extends OutputStream {
    private boolean closed = false;
    @Override
    public void write(int b) throws IOException {
        if (closed) {
            throw new IOException("Write to closed stream");
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException("data is null");
        }
        if (closed) {
            throw new IOException("Write to closed stream");
        }
    }

    @Override
    public void close() throws IOException {
        closed = true;
    }
}
