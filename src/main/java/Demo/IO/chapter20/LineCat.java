package Demo.IO.chapter20;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/16 17:32
 */
public class LineCat {
    public static void main(String[] args) {
        String thisLine;
        for (String arg : args) {
            try (LineNumberReader lnr = new LineNumberReader(new FileReader(arg))) {
                while ((thisLine = lnr.readLine()) != null) {
                    System.out.println(lnr.getLineNumber() + ": " + thisLine);
                }
            } catch (IOException ignored) {
            }
        }
    }
}
