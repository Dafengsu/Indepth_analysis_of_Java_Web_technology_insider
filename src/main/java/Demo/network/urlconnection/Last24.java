package Demo.network.urlconnection;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * @description:
 * @author: su
 * @date: 2020/2/4
 */
public class Last24 {
    public static void main(String[] args) {
        Date today = new Date();
        long millionsSecondsPerDay = 1000 * 60 * 60 * 24;
        for (String arg : args) {
            try {
                URL u = new URL(arg);
                URLConnection uc = u.openConnection();
                System.out.println("Original if modified since: " + new Date(uc.getIfModifiedSince()));
                uc.setIfModifiedSince((new Date(today.getTime() - millionsSecondsPerDay)).getTime());
                System.out.println("Will retrieve file if it's modified since " + new Date(uc.getIfModifiedSince()));
                try (InputStream in = new BufferedInputStream(uc.getInputStream())) {
                    Reader r = new InputStreamReader(in);
                    int c;
                    while ((c = in.read())!=-1) System.out.print((char) c);
                    System.out.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
