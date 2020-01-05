package Demo.IO.chapter06;

import java.io.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 20:51
 */
public class StringExtractor {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java StringExtractor inFile");
            return;
        }
        try {
            FileInputStream in = new FileInputStream(args[0]);
            OutputStream out;
            if (args.length >= 2) {
                out = new FileOutputStream(args[1]);
            } else {
                out = System.out;
            }
            PrintableOutputStream pout = new PrintableOutputStream(out);
            for (int c = in.read(); c != -1; c = in.read()) {
                pout.write(c);
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Usage: java StringExtractor inFile outFile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
