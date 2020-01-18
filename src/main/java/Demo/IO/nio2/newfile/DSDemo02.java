package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 20:21
 */
public class DSDemo02 {
    public static void main(String[] args) {
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get("."), "*.xml")) {
            ds.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
