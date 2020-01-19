package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/19 17:27
 */
public class Move {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("usage: java Move srcPath destPath");
            return;
        }
        class MoveVisitor extends SimpleFileVisitor<Path> {
            private Path srcPath, dstPath;

            public MoveVisitor(Path srcPath, Path dstPath) {
                this.srcPath = srcPath;
                this.dstPath = dstPath;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc == null) {
                    Files.delete(dir);
                } else {
                    throw exc;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetPath = dstPath.resolve(srcPath.relativize(dir));
                Files.copy(dir, targetPath, StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path targetPath = dstPath.resolve(srcPath.relativize(file));
                Files.move(file, targetPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
                return FileVisitResult.CONTINUE;
            }
        }

        Path src = Paths.get(args[0]);
        Path dst = Paths.get(args[1]);
        Files.walkFileTree(src, new MoveVisitor(src, dst));
    }
}
