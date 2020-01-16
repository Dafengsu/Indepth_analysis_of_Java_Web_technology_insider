package Demo.IO.chapter20;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/16 19:37
 */
public class SourceWriter extends FilterWriter {
    public SourceWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) write(cbuf[i]);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) write(str.charAt(i));
    }

    @Override
    public void write(int c) throws IOException {
        if (c == '\\') out.write("\\u005c");
        else if (c < 128) out.write(c);
        else {
            String s = Integer.toHexString(c);
            if (c<256) s = "00" + s;
            else if (c < 4096) s = "0" + s;
            out.write("\\u");
            out.write(s);
        }
    }
}
