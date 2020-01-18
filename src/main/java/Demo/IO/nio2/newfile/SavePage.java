package Demo.IO.nio2.newfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 19:37
 */
public class SavePage {
    public static void main(String[] args) throws IOException {
        Files.write(Paths.get("page.html"), new BufferedReader(new InputStreamReader(
                new URL("https://www.researchgate.net/").openStream()))
                .lines().collect(Collectors.toList()));
    }
}
