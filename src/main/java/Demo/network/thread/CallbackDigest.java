package Demo.network.thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static Demo.network.thread.CallbackDigestUserInterface.receiveDigest;

/**
 * @description:
 * @author: su
 * @date: 2020/1/27
 */
public class CallbackDigest implements Runnable {

    private String filename;

    public CallbackDigest(String filename) {
        this.filename = filename;
    }


    @Override
    public void run() {
        try {
            byte[] digest = InstanceCallbackDigest.digest(filename);
            receiveDigest(digest, filename);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
