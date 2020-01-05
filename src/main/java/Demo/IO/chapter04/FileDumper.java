package Demo.IO.chapter04;

import Demo.IO.chapter03.StreamCopier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 11:59
 */
public class FileDumper {
    public static final int ASC = 0;
    public static final int DEC = 1;
    public static final int HEX = 2;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java FileDumper [-ahd] file1 file2...");
            return;
        }
        int firstArg = 0;
        int mode = ASC;
        if (args[0].startsWith("-")) {
            firstArg = 1;
            if (args[0].equals("-h")) {
                mode = HEX;
            } else if (args[0].equals("-d")) {
                mode = DEC;
            }
        }
        for (int i = firstArg; i < args.length; i++) {
            try {
                if (mode == ASC) {
                    dumpAscii(args[i]);
                } else if (mode == HEX) {
                    dumpHex(args[i]);
                } else if (mode == DEC) {
                    dumpDecimal(args[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void dumpDecimal(String filename) throws IOException {
        try (FileInputStream fin = new FileInputStream(filename)) {
            byte[] buffer = new byte[16];
            boolean end = false;
            while (!end) {
                int bytesRead = 0;
                while (bytesRead < buffer.length) {
                    int r = fin.read(buffer, bytesRead, buffer.length - bytesRead);
                    if (r == -1) {
                        end = true;
                        break;
                    }
                    bytesRead += r;
                }
                for (int i = 0; i < bytesRead; i++) {
                    int dec = buffer[i];
                    if (dec < 0) {
                        dec = 256 + dec;
                    }
                    if (dec < 10) {
                        System.out.print("00" + dec + " ");
                    } else if (dec < 100) {
                        System.out.print("0" + dec + " ");
                    } else {
                        System.out.print(dec + " ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void dumpHex(String filename) throws IOException {
        try (FileInputStream fin = new FileInputStream(filename)) {
            byte[] buffer = new byte[24];
            boolean end = false;
            while (!end) {
                int bytesRead = 0;
                while (bytesRead < buffer.length) {
                    int r = fin.read(buffer, bytesRead, buffer.length - bytesRead);
                    if (r == -1) {
                        end = true;
                        break;
                    }
                    bytesRead += r;
                }
                for (int i = 0; i < bytesRead; i++) {
                    int hex = buffer[i];
                    if (hex < 0) hex = 256 + hex;
                    if (hex >= 16) {
                        System.out.print(Integer.toHexString(hex) + " ");
                    } else {
                        System.out.print("0" + Integer.toHexString(hex) + " ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void dumpAscii(String filename) throws IOException {
        try (FileInputStream fin = new FileInputStream(filename)) {
            StreamCopier.copy(fin, System.out);
        }
    }
}
