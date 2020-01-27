package Demo.network.thread;

import javax.xml.bind.DatatypeConverter;

/**
 * @description:
 * @author: su
 * @date: 2020/1/27
 */
public class CallbackDigestUserInterface {
    public static void receiveDigest(byte[] digest, String filename) {
        System.out.println(filename + ": " + DatatypeConverter.printHexBinary(digest));
    }

    public static void main(String[] args) {
        for (String filename : args) {
            new Thread(new CallbackDigest(filename)).start();
        }
    }
}
