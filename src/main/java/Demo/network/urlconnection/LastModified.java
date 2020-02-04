package Demo.network.urlconnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * @description:
 * @author: su
 * @date: 2020/2/4
 */
public class LastModified {
    public static void main(String[] args) {
        for (String arg : args) {
            try {
                URL u = new URL(arg);
                HttpURLConnection huc = (HttpURLConnection) u.openConnection();
                huc.setRequestMethod("HEAD");
                System.out.println(u + " was last modified at " +
                        new Date(huc.getLastModified()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
