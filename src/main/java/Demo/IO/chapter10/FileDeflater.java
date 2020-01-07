package Demo.IO.chapter10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 14:15
 */
public class FileDeflater {
    public static final String DEFLATE_SUFFIX = ".dfl";

    public static void main(String[] args) {
        for (String arg : args) {
            try (FileInputStream fin = new FileInputStream(arg);
                 FileOutputStream fout = new FileOutputStream(arg + DEFLATE_SUFFIX);
                 DeflaterOutputStream dos = new DeflaterOutputStream(fout)) {
                for (int c = fin.read(); c != -1; c = fin.read()) {
                    dos.write(c);
                }
            } catch (IOException e) {
            };
        }
    }
}
