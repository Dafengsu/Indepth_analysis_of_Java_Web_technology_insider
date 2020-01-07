package Demo.IO.chapter10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 21:24
 */
public class Unzipper2 {
    public static void main(String[] args) throws IOException {
        for (String arg : args) {
            FileInputStream fin = new FileInputStream(arg);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                System.out.println("Unzipping " + ze.getName());
                FileOutputStream fout = new FileOutputStream(ze.getName());
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                zin.closeEntry();
                fout.close();
            }
            zin.close();
        }
    }
}
