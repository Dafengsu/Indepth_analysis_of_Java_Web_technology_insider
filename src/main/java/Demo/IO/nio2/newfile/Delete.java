package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/19 17:19
 */
public class Delete {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java Delete path");
            return;
        }
        class DeleteVisitor extends SimpleFileVisitor<Path> {
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc == null) {
                    if (Files.deleteIfExists(dir)) {
                        System.out.printf("deleted directory %s%n", dir);
                    } else {
                        System.out.println("couldn't delete directory " + dir);
                    }
                } else {
                    throw exc;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (Files.deleteIfExists(file))
                    System.out.printf("deleted regular file %s%n", file);
                else
                    System.out.printf("couldn't delete regular file %s%n", file);
                return FileVisitResult.CONTINUE;
            }
        }

        Files.walkFileTree(Paths.get(args[0]), new DeleteVisitor());
    }
}
