package Demo.IO.chapter12;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/8 14:29
 */
public class TrueMirror {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        if (args.length != 2) {
            System.err.println("Usage: java TrueMirror url1 url2");
            return;
        }
        URL source = new URL(args[0]);
        URL mirror = new URL(args[1]);
        byte[] sourceDigest = getDigestFromURL(source);
        byte[] mirrorDigest = getDigestFromURL(mirror);
        if (MessageDigest.isEqual(sourceDigest, mirrorDigest)) {
            System.out.println(mirror + " is up to date");
        } else {
            System.out.println(mirror + " needs to be updated");
        }
    }

    public static byte[] getDigestFromURL(URL u) throws NoSuchAlgorithmException, IOException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        InputStream in = u.openStream();
        byte[] data = new byte[128];
        while (true) {
            int bytesRead = in.read(data);
            if (bytesRead < 0) {
                break;
            }
            md5.update(data, 0, bytesRead);
        }
        return md5.digest();
    }
}
