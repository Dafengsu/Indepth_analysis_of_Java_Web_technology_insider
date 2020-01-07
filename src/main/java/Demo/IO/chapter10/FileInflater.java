package Demo.IO.chapter10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 15:57
 */
public class FileInflater {
    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.toLowerCase().endsWith(FileDeflater.DEFLATE_SUFFIX)) {
                try (FileInputStream fin = new FileInputStream(arg);
                     InflaterInputStream iis = new InflaterInputStream(fin);
                     FileOutputStream fout = new FileOutputStream(arg.substring(0, arg.length() - 4))) {
                    for (int c = iis.read(); c != -1; c = iis.read()) {
                        fout.write(c);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(arg+" does not appear to be deflated file.");
            }

        }
    }
}
