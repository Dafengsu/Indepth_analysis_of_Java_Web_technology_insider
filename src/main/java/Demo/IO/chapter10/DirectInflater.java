package Demo.IO.chapter10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 13:51
 */
public class DirectInflater {
    public static void main(String[] args) {
        Inflater inf = new Inflater();
        byte[] input = new byte[1024];
        byte[] output = new byte[1024];
        for (String arg : args) {
            try {
                if (!arg.endsWith(DirectDeflater.DEFLATE_SUFFIX)) {
                    System.out.println(arg + " does not look like a deflated file");
                    continue;
                }
                FileInputStream fin = new FileInputStream(arg);
                FileOutputStream fout = new FileOutputStream(arg.substring(0, arg.length() - DirectDeflater.DEFLATE_SUFFIX.length()));
                while (true) {
                    int numRead = fin.read(input);
                    if (numRead != -1) {
                        inf.setInput(input);
                    }
                    int numDecompressed = 0;
                    while ((numDecompressed = inf.inflate(output)) != 0) {
                        fout.write(output, 0, numDecompressed);
                    }
                    if (inf.finished()) {
                        break;
                    } else if (inf.needsDictionary()) {
                        System.err.println("Dictionary required! bailing...");
                    } else if (inf.needsInput()) {
                        continue;
                    }

                }
                fin.close();
                fout.flush();
                fout.close();
                inf.reset();
                inf.reset();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DataFormatException e) {
                System.err.println(arg + " appears to be corrupt");
                e.printStackTrace();
            }
        }
    }
}
