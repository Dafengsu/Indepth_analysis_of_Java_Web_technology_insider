package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 20:10
 */
public class CTDDemo {
    public static void main(String[] args) throws IOException {
        Path path = Files.createTempDirectory(Paths.get("."), "image");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

    }
}
