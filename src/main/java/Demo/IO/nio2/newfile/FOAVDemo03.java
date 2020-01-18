package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 18:27
 */
public class FOAVDemo03 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1)
        {
            System.err.println("usage: java FOAVDemo path");
            return;
        }
        Path path = Paths.get(args[0]);
        System.out.printf("Owner: %s%n", Files.getOwner(path));
        UserPrincipal up = path.getFileSystem().
                getUserPrincipalLookupService().
                lookupPrincipalByName("Administrator");
        System.out.println(up);
        Files.setOwner(path, up);
        System.out.printf("Owner: %s%n", Files.getOwner(path));
    }
}
