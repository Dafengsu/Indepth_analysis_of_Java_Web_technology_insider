package Demo.IO.chapter06;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 20:44
 */
public class PrintableOutputStream extends FilterOutputStream {

    /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public PrintableOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void write(int b) throws IOException {
        if (b == '\n' || b == '\r' || b == '\t') {
            out.write(b);
        } else if (b < 32 || b > 126) {
            out.write('?');
        } else {
            out.write(b);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            this.write(b[i]);
        }
    }
}
