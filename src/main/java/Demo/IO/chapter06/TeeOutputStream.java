package Demo.IO.chapter06;

import java.io.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 21:57
 */
public class TeeOutputStream extends FilterOutputStream {
    private OutputStream out1;
    private OutputStream out2;

    public TeeOutputStream(OutputStream out1, OutputStream out2) {
        super(out1);
        this.out1 = out1;
        this.out2 = out2;
    }

    @Override
    public void write(int b) throws IOException {
        out1.write(b);
        out2.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out1.write(b, off, len);
        out2.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        out1.flush();
        out2.flush();
    }

    @Override
    public void close() throws IOException {
        out1.close();
        out2.close();
    }
}
