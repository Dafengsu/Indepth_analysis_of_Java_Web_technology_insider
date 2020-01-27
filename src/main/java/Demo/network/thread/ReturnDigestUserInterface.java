package Demo.network.thread;

import javax.xml.bind.DatatypeConverter;

/**
 * @description:
 * @author: su
 * @date: 2020/1/27
 */
public class ReturnDigestUserInterface {
    public static void main(String[] args) throws InterruptedException {
        ReturnDigest[] digests = new ReturnDigest[args.length];
        for (int i = 0, argsLength = args.length; i < argsLength; i++) {
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }
        for (ReturnDigest digest : digests) {
            while (true) {
                byte[] result = digest.getDigest();
                if (result != null) {
                    System.out.println(digest.getFilename() + ": " + DatatypeConverter.printHexBinary(result));
                    break;
                }
            }

        }
    }
}
