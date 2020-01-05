package Demo.IO.chapter05;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 13:10
 */
public class URLTyper {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java URLTyper url");
            return;
        }
        try (InputStream in = new URL(args[0]).openStream()){
            for (int c = in.read(); c != -1; c = in.read()) {
                System.out.write(c);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
