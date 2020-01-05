package Demo.IO.chapter06;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 22:11
 */
public abstract class DumpFilter extends FilterInputStream {
    public int[] buf = new int[0];
    public int index = 0;

    public DumpFilter(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int result;
        if (index < buf.length) {
            result = buf[index];
            index++;
        } else {
            try {
                fill();
                result = buf[0];
                index = 1;
            } catch (EOFException ex) {
                result = -1;
            }
        }
        return result;
    }

    protected abstract void fill() throws IOException;

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if ((off < 0) || (off > b.length) || (len < 0)
                || ((off + len) > b.length) || (off + len) < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }
        int datum = this.read();
        if (datum == -1) {
            return -1;
        }
        b[off] = (byte) datum;
        int bytesRead = 1;
        try {
            for (; bytesRead < len; bytesRead++) {
                datum = this.read();
                if (datum == -1) {
                    break;
                }
                b[off + bytesRead] = (byte) datum;
            }
        } catch (IOException ignored) {

        }
        return bytesRead;
    }

    @Override
    public int available() throws IOException{
        return buf.length - index;
    }

    @Override
    public long skip(long n) throws IOException {
        long bytesSkipped = 0;
        for (; bytesSkipped < n; bytesSkipped++) {
            int c = read();
            if (c == -1) {
                break;
            }
        }
        return bytesSkipped;
    }

    @Override
    public synchronized void mark(int readlimit) {

    }

    @Override
    public synchronized void reset() throws IOException {
        throw new IOException("marking not supported");
    }

    @Override
    public boolean markSupported() {
        return false;
    }
}
