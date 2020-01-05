package Demo.IO.chapter06;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 22:35
 */
public class HexFilter extends DumpFilter{
    private int numRead = 0;
    private int breakAfter = 15;
    private int ratio = 3;

    public HexFilter(InputStream in) {
        super(in);
    }

    @Override
    protected void fill() throws IOException {
        buf = new int[ratio];
        int datum = in.read();
        numRead++;
        if (datum == -1) {
            throw new EOFException();
        }
        String hex = Integer.toHexString(datum);
        if (datum < 16) {
            hex += '0';
        }
        for (int i = 0; i < hex.length(); i++) {
            buf[i] = hex.charAt(i);
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
