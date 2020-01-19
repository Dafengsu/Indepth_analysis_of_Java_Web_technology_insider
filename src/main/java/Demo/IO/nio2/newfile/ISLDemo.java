package Demo.IO.nio2.newfile;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/19 10:30
 */
public class ISLDemo {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: java ISLDemo path");
            return;
        }
        if (Files.isSymbolicLink(Paths.get(args[0])))
            System.out.println("is symbolic link");
        else
            System.out.println("is not symbolic link");
    }
}
