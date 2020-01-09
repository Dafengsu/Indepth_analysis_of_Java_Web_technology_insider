package Demo.IO.chapter11;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/8 9:26
 */
public class JarLister {
    public static void main(String[] args) throws IOException {
        JarFile jf = new JarFile(args[0]);
        Enumeration<JarEntry> e = jf.entries();
        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            String name = je.getName();
            Date lastModified = new Date(je.getTime());
            long uncompressedSize = je.getSize();
            long compressedSize = je.getCompressedSize();
            long crc = je.getCrc();
            int method = je.getMethod();
            String comment = je.getComment();
            if (method == ZipEntry.STORED) {
                System.out.println(name + " was stored at " + lastModified);
                System.out.println("with a size of " + uncompressedSize + " bytes");
            } else if (method == ZipEntry.DEFLATED) {
                System.out.println(name + " was deflated at " + lastModified);
                System.out.println("from " + uncompressedSize + " bytes to "
                        + compressedSize + " bytes, a savings of "
                        + (100.0 - 100.0 * compressedSize / uncompressedSize) + "%");
            } else {
                System.out.println(name
                        + " was compressed using an unrecognized method at "
                        + lastModified);
                System.out.println("from " + uncompressedSize + " bytes to "
                        + compressedSize + " bytes, a savings of "
                        + (100.0 - 100.0*compressedSize/uncompressedSize) + "%");
            }

            System.out.println("Its CRC is " + crc);
            if (comment != null && !comment.equals("")) {
                System.out.println(comment);
            }
            if (je.isDirectory()) {
                System.out.println(name + " is a directory");
            }
            Attributes a = je.getAttributes();
            if (a != null) {
                Object[] nameValuePairs = a.entrySet().toArray();
                for (Object nameValuePair : nameValuePairs) {
                    System.out.println(nameValuePair);
                }
            }
            System.out.println();
        }
    }
}
