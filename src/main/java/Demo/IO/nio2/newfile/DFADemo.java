package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 18:15
 */
public class DFADemo {
    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
        {
            System.err.println("usage: java DFAVDemo path");
            return;
        }
        Path path = Paths.get(args[0]);
        DosFileAttributes dfa;
        dfa = Files.readAttributes(path, DosFileAttributes.class);
        System.out.printf("Is archive: %b%n", dfa.isArchive());
        System.out.printf("Is hidden: %b%n", dfa.isHidden());
        System.out.printf("Is readonly: %b%n", dfa.isReadOnly());
        System.out.printf("Is system: %b%n", dfa.isSystem());
    }
}
