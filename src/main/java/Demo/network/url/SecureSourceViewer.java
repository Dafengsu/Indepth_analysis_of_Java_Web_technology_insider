package Demo.network.url;

import java.io.*;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @description:
 * @author: su
 * @date: 2020/2/2
 */
public class SecureSourceViewer {
    public static void main(String[] args) {
        Authenticator.setDefault(new DialogAuthenticator());
        for (String arg : args) {
            try {
                URL u = new URL(arg);
                try (InputStream in = new BufferedInputStream(u.openStream())) {
                    Reader r = new InputStreamReader(in);
                    int c;
                    while ((c = in.read())!= -1) System.out.println((char) c);

                }
            } catch (MalformedURLException e) {
                System.err.println(arg + " is not a parseable URL");
            }catch (IOException e) {
                System.err.println(e);
            }
            System.out.println();
        }
        System.exit(0);
    }
}
