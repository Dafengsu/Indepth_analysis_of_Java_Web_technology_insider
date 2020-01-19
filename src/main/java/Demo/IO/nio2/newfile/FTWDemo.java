package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/19 13:13
 */
public class FTWDemo {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage:java FTWDemo path");
            return;
        }
        class DoNothingVisitor extends SimpleFileVisitor<Path> {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.printf("preVisitDirectory: %s%n", dir);
                System.out.printf(" lastModifiedTime: %s%n",
                        attrs.lastModifiedTime());
                System.out.printf(" size: %d%n%n", attrs.size());
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.printf("visitFile: %s%n%n", file);
                System.out.printf(" lastModifiedTime: %s%n",
                        attrs.lastModifiedTime());
                System.out.printf(" size: %d%n%n", attrs.size());
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.printf("visitFileFailed: %s %s%n%n", file, exc);
                return super.visitFileFailed(file, exc);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.printf("postVisitDirectory: %s %s%n%n", dir, exc);
                return super.postVisitDirectory(dir, exc);
            }
        }
        Files.walkFileTree(Paths.get(args[0]), new DoNothingVisitor());
    }
}
