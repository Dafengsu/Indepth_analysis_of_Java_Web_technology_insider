package Demo.IO.chapter17;

import java.io.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 12:03
 */
public class SafeFileCopier {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java FileCopier infile outfile");
            return;
        } else {
            copy(new File(args[0]), new File(args[1]));
        }

    }

    public static void copy(File inFile, File outFile) throws IOException {
        if (inFile.getCanonicalPath().equals(outFile.getCanonicalPath())) {
            return;
        }
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFile));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile))) {
            for (int c = in.read(); c != -1; c = in.read()) {
                out.write(c);
            }
        }
    }
}
