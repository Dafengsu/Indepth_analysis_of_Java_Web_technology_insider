package Demo.IO.Chapter08;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 8:19
 */
public class RootsFile {
    public static void main(String[] args) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/main/resources/roots.dat"))) {
            for (int i = 0; i < 1000; i++) {
                dos.writeFloat(((float) Math.sqrt(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
