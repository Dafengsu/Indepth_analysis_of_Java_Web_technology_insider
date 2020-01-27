package Demo.network.thread;

import javax.xml.bind.DatatypeConverter;

/**
 * @description:
 * @author: su
 * @date: 2020/1/27
 */
public class InstanceCallbackDigestUserInterface {
    private String filename;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String filename) {
        this.filename = filename;
    }

    public void calculateDigest() {
        InstanceCallbackDigest cb = new InstanceCallbackDigest(filename, this);
        new Thread(cb).start();
    }

    void receiveDigest(byte[] digest) {
        this.digest = digest;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return filename + ": " + DatatypeConverter.printHexBinary(digest);
    }

    public static void main(String[] args) {
        for (String filename : args) {
            InstanceCallbackDigestUserInterface d = new InstanceCallbackDigestUserInterface(filename);
            d.calculateDigest();
        }
    }
}
