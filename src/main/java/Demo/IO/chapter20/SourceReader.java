package Demo.IO.chapter20;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/16 19:12
 */
public class SourceReader  extends FilterReader {

    public SourceReader(Reader in) {
        super(in);
    }

    private int buffer = -1;
    @Override
    public int read() throws IOException {
        if (buffer != -1) {
            int c = buffer;
            buffer = -1;
            return c;
        }
        int c = in.read();
        if (c != '\\') {
            return c;
        }
        int next = in.read();
        if (next != 'u') {
            buffer = next;
            return c;
        }
        String hex = new StringBuilder().append((char) in.read()).append((char) in.read()).append((char) in.read()).append((char) in.read()).toString();
        try {
            return Integer.valueOf(hex, 16);
        } catch (NumberFormatException e) {
            throw new IOException("Bad Unicode escape:\\u" + hex);
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {

        int numRead = 0;
        for (int i = off; i < off + len; i++) {
            int temp = read();
            if (temp == -1) return -1;
            cbuf[i] = (char) temp;
            numRead++;
        }
        return numRead;
    }

    @Override
    public long skip(long n) throws IOException {
        return read(new char[(int) n]);
    }
}
