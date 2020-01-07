package Demo.IO.chapter10;

import Demo.IO.Demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 12:16
 */
public class DirectDeflater {
    public static final String DEFLATE_SUFFIX = ".dfl";

    public static void main(String[] args) throws IOException {
        Deflater def = new Deflater();
        byte[] input = new byte[1024];
        byte[] output = new byte[1024];
        for (String arg : args) {
            FileInputStream fin = new FileInputStream(arg);
            FileOutputStream fout = new FileOutputStream(arg + DEFLATE_SUFFIX);
            while (true) {
                int numRead = fin.read(input);
                if (numRead == -1) {
                    def.finish();
                    while (!def.finished()) {
                        int numCompressedBytes = def.deflate(output);
                        if (numCompressedBytes > 0) {
                            fout.write(output, 0, numCompressedBytes);
                        }
                    }
                    break;
                } else {
                    def.setInput(input, 0, numRead);
                    while (!def.needsInput()) {
                        int numCompressedBytes = def.deflate(output);
                        if (numCompressedBytes > 0) {
                            fout.write(output, 0, numCompressedBytes);
                        }
                    }
                }
            }
            fin.close();
            fout.flush();
            fout.close();
            def.reset();
        }
    }

}
