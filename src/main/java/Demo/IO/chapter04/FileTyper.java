package Demo.IO.chapter04;

import Demo.IO.chapter03.StreamCopier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 11:36
 */
public class FileTyper {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java FileTyper filename");
            return;
        }
        typeFile(args[0]);
    }

    private static void typeFile(String filename) throws IOException {
        FileInputStream fin = new FileInputStream(filename);
        try {
            StreamCopier.copy(fin, System.out);
        }finally {
            fin.close();
        }
    }
}
