package Demo.network.thread;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * @author: su
 * @date: 2020/1/27
 */
public class DigestRunnable implements Runnable {
    private String filename;

    public DigestRunnable(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        digest(filename);
    }

    static void digest(String filename) {
        try {
            byte[] digest = InstanceCallbackDigest.digest(filename);

            System.out.println(filename + ": " + DatatypeConverter.printHexBinary(digest));
        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println(e);
        }

    }

    public static void main(String[] args) {
        for (String filename : args) new Thread(new DigestRunnable(filename)).start();
    }
}
