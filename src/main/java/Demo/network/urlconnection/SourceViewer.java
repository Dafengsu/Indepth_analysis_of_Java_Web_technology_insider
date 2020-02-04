package Demo.network.urlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @description:
 * @author: su
 * @date: 2020/2/3
 */
public class SourceViewer {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                URL u = new URL(args[0]);
                URLConnection uc = u.openConnection();
                try (InputStream in = uc.getInputStream()) {
                    int c;
                    while ((c = in.read())!=-1) System.out.print((char) c);
                }
            } catch (MalformedURLException e) {
                System.err.println(args[0] + " is not a parseable URL");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
