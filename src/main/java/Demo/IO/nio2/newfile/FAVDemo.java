package Demo.IO.nio2.newfile;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 17:55
 */
public class FAVDemo {
    public static void main(String[] args) {
        FileSystems.getDefault().supportedFileAttributeViews()
                .forEach(System.out::println);
        System.out.printf("Supports basic: %b%n",
                isSupported(BasicFileAttributeView.class));
        System.out.printf("Supports posix: %b%n",
                isSupported(PosixFileAttributeView.class));
        System.out.printf("Supports acl: %b%n",
                isSupported(AclFileAttributeView.class));
    }

    private static boolean isSupported(Class<? extends FileAttributeView> clazz) {
        return Files.getFileAttributeView(Paths.get("."), clazz) != null;
    }
}
