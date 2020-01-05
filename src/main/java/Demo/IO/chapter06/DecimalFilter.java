package Demo.IO.chapter06;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 22:28
 */
public class DecimalFilter extends DumpFilter{
    private int numRead = 0;
    private int breakAfter = 15;
    private int ratio = 4;

    public DecimalFilter(InputStream in) {
        super(in);
    }

    @Override
    protected void fill() throws IOException {
        buf = new int[ratio];
        int datum = in.read();
        this.numRead++;
        if (datum == -1) {
            throw new EOFException();
        }
        String dec = Integer.toString(datum);
        if (datum < 10) {
            dec = "00" + dec;
        } else if (datum < 100) {
            dec = '0' + dec;
        }
        for (int i = 0; i < dec.length(); i++) {
            buf[i] = dec.charAt(i);
        }
        if (numRead < breakAfter) {
            buf[buf.length - 1] = ' ';
        } else {
            buf[buf.length - 1] = '\n';
            numRead = 0;
        }
    }

    @Override
    public int available() throws IOException {
        return (buf.length - index) + ratio * in.available();
    }
}
