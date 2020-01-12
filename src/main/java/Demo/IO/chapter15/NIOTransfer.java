package Demo.IO.chapter15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/12 16:15
 */
public class NIOTransfer {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java NIOTransfer srcFile targetFile");
            return;
        }
        FileChannel in = new FileInputStream(args[0]).getChannel();
        FileChannel out = new FileOutputStream(args[1]).getChannel();
        in.transferTo(0, in.size(), out);
        in.close();
        out.close();
    }
}
