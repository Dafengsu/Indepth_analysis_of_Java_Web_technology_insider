package Demo.IO.chapter10;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 16:41
 */
public class Unzipper {
    public static void main(String[] args) throws IOException {
        ZipFile zf = new ZipFile(args[0]);
        Enumeration<? extends ZipEntry> e = zf.entries();
        while (e.hasMoreElements()) {
            ZipEntry ze = e.nextElement();
            System.out.println("Unzipping " + ze.getName());
            FileOutputStream fout = new FileOutputStream(ze.getName());
            InputStream in = zf.getInputStream(ze);
            for (int c = in.read(); c != -1; c = in.read()) {
                fout.write(c);
            }
            in.close();
            fout.close();
        }
    }
}
