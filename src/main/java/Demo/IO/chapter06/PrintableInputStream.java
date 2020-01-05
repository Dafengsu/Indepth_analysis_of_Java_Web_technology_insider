package Demo.IO.chapter06;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 20:58
 */
public class PrintableInputStream extends FilterInputStream {

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected PrintableInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int b = in.read();
        if (b >= 32 && b <= 126) {
            return b;
        } else if (b == '\n' || b == '\r' || b == '\t') {
            return b;
        } else {
            return '?';
        }
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = in.read(b, off, len);
        for (int i = off; i < off + len; i++) {
            if (b[i] == '\n' || b[i] == '\r' || b[i] == '\t' || b[i] == -1) {

            } else if (b[i] < 32 || b[i] > 126) {
                b[i] = (byte) '?';
            }
        }
        return result;
    }
}
