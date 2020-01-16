package Demo.IO.chapter20;

import java.io.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/16 14:54
 */
public class StreamRecoder {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java StreamRecoder infile_encoding outfile_encoding infile outfile");
            return;
        }
        File infile = new File(args[2]);
        File outfile = new File(args[3]);
        if (outfile.exists() && infile.getCanonicalPath().endsWith(outfile.getCanonicalPath())) {
            System.err.println("Can't convert file in place");
            return;
        }
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(infile), args[0]);
             OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outfile), args[1])) {
            while (true) {
                int c = isr.read();
                if (c == -1) break;
                osw.write(c);
            }
        }
    }
}
