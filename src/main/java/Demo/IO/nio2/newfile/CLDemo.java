package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/19 12:46
 */
public class CLDemo {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("usage: java CLDemo linkPath existFilePath");
            return;
        }
        Files.createLink(Paths.get(args[0]), Paths.get(args[1]));
    }
}
