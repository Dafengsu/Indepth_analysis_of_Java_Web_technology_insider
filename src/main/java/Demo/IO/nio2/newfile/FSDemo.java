package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 17:45
 */
public class FSDemo {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java FSDemo path");
            return;
        }
        FileStore fs = Files.getFileStore(Paths.get(args[0]));
        System.out.printf("Total space: %d%n", fs.getTotalSpace());
        System.out.printf("Unallocated space: %d%n",
                fs.getUnallocatedSpace());
        System.out.printf("Usable space: %d%n",
                fs.getUsableSpace());
        System.out.printf("Read only: %b%n", fs.isReadOnly());
        System.out.printf("Name: %s%n", fs.name());
        System.out.printf("Type: %s%n%n", fs.type());
        FileSystem fsDefault = FileSystems.getDefault();
        fsDefault.getFileStores().forEach(fileStore -> System.out.printf("FileStore: %s%n", fileStore));
    }
}
