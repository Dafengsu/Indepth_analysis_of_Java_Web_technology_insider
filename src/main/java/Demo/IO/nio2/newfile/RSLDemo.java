package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/19 10:58
 */
public class RSLDemo {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java ISLDemo linkPath");
            return;
        }
        if (!Files.isSymbolicLink(Paths.get(args[0]))) {
            System.out.println("is not symbolic link");
        } else {
            Path targetPath = Files.readSymbolicLink(Paths.get(args[0]));
            System.out.println(targetPath);
        }
    }
}
