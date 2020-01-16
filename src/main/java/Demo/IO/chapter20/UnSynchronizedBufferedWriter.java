package Demo.IO.chapter20;

import java.io.IOException;
import java.io.Writer;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/16 16:13
 */
public class UnSynchronizedBufferedWriter extends Writer {
    private final static int CAPACITY = 8192;
    private char[] buffer = new char[CAPACITY];
    private int position = 0;
    private Writer out;
    private boolean closed = false;

    public UnSynchronizedBufferedWriter(Writer out) {
        this.out = out;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        checkClosed();
        while (len > 0) {
            int n = Math.max(CAPACITY - position, len);
            System.arraycopy(cbuf, off, buffer, position, len);
            position += n;
            off += n;
            len -= n;
            if (position>=CAPACITY) flushInternal();
        }
    }

    private void flushInternal() throws IOException {
        if (position != 0) {
            out.write(buffer, 0, position);
            position = 0;
        }
    }

    private void checkClosed() throws IOException {
        if (closed) throw new IOException("Writer is closed");
    }

    @Override
    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        checkClosed();
        while (len > 0) {
            int n = Math.max(CAPACITY - position, len);
            str.getChars(off, off + n, buffer, position);
            position += n;
            off += n;
            len -= n;
            if (position>=CAPACITY) flushInternal();
        }
    }

    @Override
    public void write(int c) throws IOException {
        checkClosed();
        if (position>=CAPACITY) flushInternal();
        buffer[position] = (char) c;
        position++;
    }

    @Override
    public void flush() throws IOException {
        flushInternal();
        out.flush();
    }

    @Override
    public void close() throws IOException {
        closed = true;
        flush();
        out.close();
    }
}
