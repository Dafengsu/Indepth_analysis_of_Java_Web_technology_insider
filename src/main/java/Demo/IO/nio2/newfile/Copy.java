package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.EnumSet;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/19 15:36
 */
public class Copy {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("usage: java copy source target");
            return;
        }
        Path source = Paths.get(args[0]);
        Path target = Paths.get(args[1]);
        if (!Files.exists(source)) {
            System.err.printf("%s source path doesn't exist%n", source);
            return;
        }
        if (!Files.isDirectory(source)) {
            if (Files.exists(target)) {
                if (Files.isDirectory(target)) {
                    target = target.resolve(source.getFileName());
                }
            }
            try {
                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                return;
            } catch (IOException e) {
                System.err.printf("I/O error: %s%n", e.getMessage());
            }
        }
        if (Files.exists(target) && !Files.isDirectory(target)) {
            System.err.printf("%s is not a directory%n", target);
            return;
        }
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        CopyVisitor copier = new CopyVisitor(source, target);
        Files.walkFileTree(source, options, Integer.MAX_VALUE, copier);
    }
    public static class CopyVisitor extends SimpleFileVisitor<Path> {
        private Path fromPath;
        private Path toPath;

        private StandardCopyOption copyOption = StandardCopyOption.REPLACE_EXISTING;

        public CopyVisitor(Path fromPath, Path toPath) {
            this.fromPath = fromPath;
            this.toPath = toPath;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("dir = " + dir);
            System.out.println("fromPath = " + fromPath);
            System.out.println("toPath = " + toPath);
            System.out.println("fromPath.relativize(dir) = " + fromPath.relativize(dir));
            System.out.println("toPath.resolve(fromPath.relativize(dir)) = " + toPath.resolve(fromPath.relativize(dir)));
            Path target = toPath.resolve(fromPath.relativize(dir));
            if (!Files.exists(target)) {
                Files.createDirectory(target);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            if (exc == null) {
                Path newDir = toPath.resolve(fromPath.relativize(dir));
                try {
                    FileTime time = Files.getLastModifiedTime(dir);
                    Files.setLastModifiedTime(newDir, time);
                } catch (IOException e) {
                    System.err.printf("cannot change lastModifiedTime: %s%n", newDir);
                }
            } else {
                System.err.println(exc);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("file = " + file);
            System.out.println("fromPath = " + fromPath);
            System.out.println("toPath = " + toPath);
            System.out.println("fromPath.relativize(file) = " +
                    fromPath.relativize(file));
            System.out.println("toPath.resolve(fromPath.relativize(file)) = " +
                    toPath.resolve(fromPath.relativize(file)));
            Files.copy(file, toPath.resolve(fromPath.relativize(file)),
                    copyOption);
            return FileVisitResult.CONTINUE;
        }
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.out.println(exc);
            return FileVisitResult.CONTINUE;
        }

    }
}
