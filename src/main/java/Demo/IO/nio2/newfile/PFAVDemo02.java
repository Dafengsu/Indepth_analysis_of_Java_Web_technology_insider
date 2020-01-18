package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 18:21
 */
public class PFAVDemo02 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1)
        {
            System.err.println("usage: java PFAVDemo path");
            return;
        }
        Path path = Paths.get(args[0]);
        PosixFileAttributes pfa;
        pfa = Files.readAttributes(path, PosixFileAttributes.class);
        System.out.printf("Group: %s%n", pfa.group());
        for (PosixFilePermission perm: pfa.permissions())
            System.out.printf("Permission: %s%n", perm);
    }
}
