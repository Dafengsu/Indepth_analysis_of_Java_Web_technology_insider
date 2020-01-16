package Demo.IO.chapter20;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/16 15:32
 */
public class BufferedBMPTable extends UnicodeBMPTable{
    public static void main(String[] args) throws FileNotFoundException {
        initially(args);
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(target, encoding))) {
            writeCharacter(out,lineSeparator);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
