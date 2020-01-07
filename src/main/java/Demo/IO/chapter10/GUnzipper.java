package Demo.IO.chapter10;

import java.io.*;
import java.util.zip.*;

public class GUnzipper {

  public static void main(String[] args) {

    for (String arg : args) {
      if (arg.toLowerCase().endsWith(GZipper.GZIP_SUFFIX)) {
        try (FileInputStream fin = new FileInputStream(arg);
             GZIPInputStream gzin = new GZIPInputStream(fin);
             FileOutputStream fout = new FileOutputStream(arg.substring(0, arg.length() - 3));) {
          for (int c = gzin.read(); c != -1; c = gzin.read()) {
            fout.write(c);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        System.err.println(arg + " does not appear to be a gzipped file.");
      }
    }
  }
}
