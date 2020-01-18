package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 20:16
 */
public class DSDemo {
    public static void main(String[] args) throws IOException {
        Files.newDirectoryStream(Paths.get(".")).forEach(System.out::println);
    }
}
