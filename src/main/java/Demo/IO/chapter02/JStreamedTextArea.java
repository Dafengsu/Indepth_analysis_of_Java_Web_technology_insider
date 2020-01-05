package Demo.IO.chapter02;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 10:30
 */
public class JStreamedTextArea extends JTextArea {
    private OutputStream theOutput = new TextAreaOutputStream();

    public JStreamedTextArea() {
        this("", 0, 0);
    }

    public JStreamedTextArea(String text) {
        this(text, 0, 0);
    }

    public JStreamedTextArea(int rows, int columns) {
        this("", rows, columns);
    }

    public OutputStream getOutputStream() {
        return theOutput;
    }

    public JStreamedTextArea(String text, int rows, int columns) {
        super(text, rows, columns);
        setEditable(false);
    }
    private class TextAreaOutputStream extends OutputStream {
        private boolean closed = false;
        @Override
        public void write(int b) throws IOException {
            checkOpen();
            b &= 0x000000FF;
            char c = (char) b;
            append(String.valueOf(c));
        }

        private void checkOpen() throws IOException {
            if (closed) {
                throw new IOException("Write to closed stream");
            }
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            checkOpen();
            append(new String(b, off, len));
        }

        @Override
        public void close() {
            this.closed = true;
        }
    }
}
