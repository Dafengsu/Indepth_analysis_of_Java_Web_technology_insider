package Demo.network.thread;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: su
 * @date: 2020/1/29
 */
public class GZipAllFiles {
    public static final int THREAD_COUNT = 4;
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        for (String filename : args) {
            File f = new File(filename);
            if (f.exists()) {
                if (f.isDirectory()) {
                    File[] files = f.listFiles();
                    for (File file : files != null ? files : new File[0]) {
                        if (!file.isDirectory()) {
                            GzipRunnable task = new GzipRunnable(file);
                            pool.submit(task);
                        }
                    }
                } else {
                    GzipRunnable task = new GzipRunnable(f);
                    pool.submit(task);
                }
            }
        }

        pool.shutdown();
    }
}
