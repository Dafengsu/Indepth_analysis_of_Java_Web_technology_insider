package Demo.network.url;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * @description:
 * @author: su
 * @date: 2020/2/1
 */
public class DMoz {
    public static void main(String[] args) {
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "2121");

        String target = "";
        for (String arg : args) {
            target += arg + " ";
        }
        target = target.trim();
        QueryString query = new QueryString();
        query.add("q", target);
        try {
            URL u = new URL("http://www.dmoz.org/search/q?" + query);
            try (InputStream in = new BufferedInputStream(u.openStream())) {
                InputStreamReader theHTML = new InputStreamReader(in);
                int c;
                while ((c = theHTML.read()) != -1) {
                    System.out.print((char) c);
                }
            }
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
