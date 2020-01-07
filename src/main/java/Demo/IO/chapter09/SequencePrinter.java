package Demo.IO.chapter09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 10:25
 */
public class SequencePrinter {
    public static void main(String[] args) throws IOException {
        Vector<FileInputStream> theStream = new Vector<>();
        for (String arg : args) {
            FileInputStream fin = new FileInputStream(arg);
            theStream.addElement(fin);
        }
        SequenceInputStream in = new SequenceInputStream(theStream.elements());
        for (int i = in.read(); i != -1; i = in.read()) {
            System.out.write(i);
        }
    }
}
