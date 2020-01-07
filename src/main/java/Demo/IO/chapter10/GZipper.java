package Demo.IO.chapter10;

import java.io.*;
import java.util.zip.*;

public class GZipper {

  public final static String GZIP_SUFFIX = ".gz";

  public static void main(String[] args) {

    for (String arg : args) {
      try(InputStream fin = new FileInputStream(arg);
          OutputStream fout = new FileOutputStream(arg + GZIP_SUFFIX);
          GZIPOutputStream gzout = new GZIPOutputStream(fout);) {
        for (int c = fin.read(); c != -1; c = fin.read()) {
          gzout.write(c);
        }
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}
