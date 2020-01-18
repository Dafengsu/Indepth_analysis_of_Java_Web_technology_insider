package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 20:26
 */
public class SavePage03 {
    public static void main(String[] args) throws IOException {
        Files.copy(new URL("https://www.researchgate.net/").openStream(), Paths.get("src/main/resources/page2.html"));
    }
}
