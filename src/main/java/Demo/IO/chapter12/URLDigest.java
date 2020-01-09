package Demo.IO.chapter12;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/8 13:58
 */
public class URLDigest {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        URL url = new URL(args[0]);
        InputStream in = url.openStream();
        MessageDigest sha = MessageDigest.getInstance("SHA");
        byte[] data = new byte[128];
        while (in.read(data) > 0) {
            int bytesRead = in.read(data);
            if (bytesRead < 0) {
                break;
            }
            sha.update(data, 0, bytesRead);
        }
        byte[] result = sha.digest();
        for (byte b : result) {
            System.out.print(b + " ");
        }
        System.out.println();
        System.out.println(new BigInteger(result));
    }
}
