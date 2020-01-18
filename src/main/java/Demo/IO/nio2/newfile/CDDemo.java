package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 20:04
 */
public class CDDemo {
    public static void main(String[] args) throws IOException {
        Files.createDirectories(Paths.get("a/b/c"));
    }
}
