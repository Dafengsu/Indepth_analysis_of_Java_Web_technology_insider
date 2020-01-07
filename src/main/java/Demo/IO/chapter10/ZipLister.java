package Demo.IO.chapter10;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 16:37
 */
public class ZipLister {
    public static void main(String[] args) throws IOException {
        ZipFile zf = new ZipFile(args[0]);
        Enumeration<? extends ZipEntry> e = zf.entries();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }
}
