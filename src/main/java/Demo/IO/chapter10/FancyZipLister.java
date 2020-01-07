package Demo.IO.chapter10;

import java.util.*;
import java.util.zip.*;
import java.io.*;

public class FancyZipLister {

  public static void main(String[] args) {

    for (String arg : args) {
      try {
        ZipFile zf = new ZipFile(arg);
        Enumeration<? extends ZipEntry> e = zf.entries();
        while (e.hasMoreElements()) {
          ZipEntry ze = e.nextElement();
          String name = ze.getName();
          Date lastModified = new Date(ze.getTime());
          long uncompressedSize = ze.getSize();
          long compressedSize = ze.getCompressedSize();
          long crc = ze.getCrc();
          int method = ze.getMethod();
          String comment = ze.getComment();

          if (method == ZipEntry.STORED) {
            System.out.println(name + " was stored at " + lastModified);
            System.out.println("with a size of  " + uncompressedSize
                    + " bytes");
          } else if (method == ZipEntry.DEFLATED) {
            System.out.println(name + " was deflated at " + lastModified);
            System.out.println("from  " + uncompressedSize + " bytes to "
                    + compressedSize + " bytes, a savings of "
                    + (100.0 - 100.0 * compressedSize / uncompressedSize) + "%");
          } else {
            System.out.println(name
                    + " was compressed using an unrecognized method at "
                    + lastModified);
            System.out.println("from  " + uncompressedSize + " bytes to "
                    + compressedSize + " bytes, a savings of "
                    + (100.0 - 100.0 * compressedSize / uncompressedSize) + "%");
          }
          System.out.println("Its CRC is " + crc);
          if (comment != null && !comment.equals("")) {
            System.out.println(comment);
          }
          if (ze.isDirectory()) {
            System.out.println(name + " is a directory");
          }
          System.out.println();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
