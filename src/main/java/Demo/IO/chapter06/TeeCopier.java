package Demo.IO.chapter06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 22:02
 */
public class TeeCopier {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: java TeeCopier infile outfile1 outfile2");
        }
        FileInputStream in = new FileInputStream(args[0]);
        FileOutputStream out1 = new FileOutputStream(args[1]);
        FileOutputStream out2 = new FileOutputStream(args[2]);
        TeeOutputStream tout = new TeeOutputStream(out1, out2);
        BufferedStreamCopier.copy(in, tout);
    }

}
