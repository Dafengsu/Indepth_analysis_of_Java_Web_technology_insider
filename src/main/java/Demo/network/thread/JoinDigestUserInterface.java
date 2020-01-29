package Demo.network.thread;

import javax.xml.bind.DatatypeConverter;

/**
 * @description:
 * @author: su
 * @date: 2020/1/29
 */
public class JoinDigestUserInterface {
    public static void main(String[] args) {
        ReturnDigest[] digestThreads = new ReturnDigest[args.length];
        for (int i = 0; i < args.length; i++) {
            digestThreads[i] = new ReturnDigest(args[i]);
            digestThreads[i].start();
        }
        for (int i = 0; i < args.length; i++) {
            try {
                digestThreads[i].join();
                System.out.println(args[i] + "： " + DatatypeConverter.printHexBinary(digestThreads[i].getDigest()));
            } catch (InterruptedException e) {
                System.err.println("Thread Interrupted before completion!");
            }
        }
    }
}
