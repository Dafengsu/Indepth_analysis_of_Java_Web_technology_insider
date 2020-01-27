package Demo.network.thread;

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
public class InstanceCallbackDigest implements Runnable{
    private String filename;
    private InstanceCallbackDigestUserInterface callback;

    public InstanceCallbackDigest(String filename, InstanceCallbackDigestUserInterface callback) {
        this.filename = filename;
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            byte[] digest = digest(filename);
            callback.receiveDigest(digest);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

    static byte[] digest(String filename) throws NoSuchAlgorithmException, IOException {
        FileInputStream in = new FileInputStream(filename);
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        DigestInputStream din = new DigestInputStream(in, sha);
        while (din.read()!=-1);
        din.close();
        return sha.digest();
    }
}
