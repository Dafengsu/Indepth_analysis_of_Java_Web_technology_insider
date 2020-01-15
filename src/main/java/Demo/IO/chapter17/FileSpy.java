package Demo.IO.chapter17;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 12:35
 */
public class FileSpy {
    public static void main(String[] args) {
        for (String arg : args) {
            File f = new File(arg);
            if (f.exists()) {
                String fName = f.getName();
                String fPath = f.getPath();
                System.out.println("Name: " + fName);
                System.out.println("Absolute path: " + f.getAbsolutePath());
                try {
                    System.out.println("Canonical path: " + f.getCanonicalPath());
                } catch (IOException e) {
                    System.out.println("Could not determine the canonical path.");
                }
                String parent = f.getParent();
                if (parent != null) {
                    System.out.println("Parent: " + parent);
                }
                if (f.canWrite()) {
                    System.out.println(fName + " is writable");
                }
                if (f.canRead()) {
                    System.out.println(fName + " is readable.");
                }
                if (f.isFile()) {
                    System.out.println(fName + " is a file");
                } else if (f.isDirectory()) {
                    System.out.println(fName + " is a directory");
                } else {
                    System.out.println("What is this");
                }
                if (f.isAbsolute()) {
                    System.out.println(fPath + " is an absolute path.");
                } else {
                    System.out.println(fPath + " is a relative path.");
                }
                long lm = f.lastModified();
                if (lm != 0) {
                    System.out.println("Last Modified at " + new Date(lm));
                }
                long length = f.length();
                if (length != 0) {
                    System.out.println(fName + " is " + length + " bytes long.");
                }
            } else {
                System.out.println("I'm sorry. I can't find the file " + arg);
            }
        }
    }
}
