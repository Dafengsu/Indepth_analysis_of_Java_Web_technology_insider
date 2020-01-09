package Demo.IO.chapter12;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/8 15:25
 */
public class FileDigest {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        if (args.length != 2) {
            System.err.println("Usage: java FileDigest url filename");
            return;
        }
        URL url = new URL(args[0]);
        FileOutputStream out = new FileOutputStream(args[1]);
        copyFileWithDigest(url.openStream(), out);
    }

    public static void copyFileWithDigest(InputStream in, OutputStream out) throws NoSuchAlgorithmException, IOException {
        MessageDigest sha = MessageDigest.getInstance("SHA-512");
        DigestOutputStream dout = new DigestOutputStream(out, sha);
        byte[] data = new byte[128];
        while (true) {
            int bytesRead = in.read(data);
            if (bytesRead < 0) {
                break;
            }
            dout.write(data, 0, bytesRead);
        }
        dout.flush();
        byte[] result = dout.getMessageDigest().digest();
        for (byte b : result) {
            System.out.print(b + " ");
        }
        System.out.println();
    }
}
