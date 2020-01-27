package Demo.network.thread;

import static Demo.network.thread.DigestRunnable.digest;

/**
 * @description:
 * @author: su
 * @date: 2020/1/27
 */
public class DigestThread extends Thread {
    private String filename;

    public DigestThread(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        digest(filename);
    }

    public static void main(String[] args) {
        for (String arg : args) new DigestThread(arg).start();
    }
}
