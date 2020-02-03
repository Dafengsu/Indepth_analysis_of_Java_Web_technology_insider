package Demo.network.http.urlconnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @description:
 * @author: su
 * @date: 2020/2/3
 */
public class AllHeaders {
    public static void main(String[] args) {
        for (String arg : args) {
            try {
                URL u = new URL(arg);
                URLConnection uc = u.openConnection();
                for (int i = 1; ; i++) {
                    String header = uc.getHeaderField(i);
                    if (header == null) break;
                    System.out.println(uc.getHeaderFieldKey(i) + ": " + header);
                }
            } catch (MalformedURLException e) {
                System.err.println(arg + " is not URL I understand.");
            } catch (IOException e) {
                System.err.println(e);
            }
            System.out.println();
        }
    }
}
