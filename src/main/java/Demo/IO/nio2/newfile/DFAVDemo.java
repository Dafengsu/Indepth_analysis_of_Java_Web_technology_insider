package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 18:16
 */
public class DFAVDemo {
    public static void main(String[] args) throws IOException {
        if (args.length < 1 || args.length > 2)
        {
            System.err.println("usage: java DFAVDemo path [set]");
            return;
        }
        Path path = Paths.get(args[0]);
        boolean setAttr = false;
        if (args.length == 2)
            setAttr = true;
        System.out.printf("Is archive: %b%n",
                Files.getAttribute(path, "dos:archive"));
        System.out.printf("Is hidden: %b%n",
                Files.getAttribute(path, "dos:hidden"));
        System.out.printf("Is readonly: %b%n",
                Files.getAttribute(path, "dos:readonly"));
        System.out.printf("Is system: %b%n",
                Files.getAttribute(path, "dos:system"));
        if (setAttr)
        {
            Files.setAttribute(path, "dos:system", true);
            System.out.printf("Is system: %s%n",
                    Files.getAttribute(path, "dos:system"));
        }
    }
}
