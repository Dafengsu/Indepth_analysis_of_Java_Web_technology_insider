package Demo.IO.nio2.newfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 19:48
 */
public class SavePage2 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.researchgate.net/");
        InputStreamReader isr = new InputStreamReader(url.openStream());
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("page.html"));
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line, 0, line.length());
            bw.newLine();
        }
        bw.close(); // You must close the file to write data to storage.
    }
}
