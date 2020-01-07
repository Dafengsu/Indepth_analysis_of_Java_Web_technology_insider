package Demo.IO.Chapter08;

import java.io.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/6 14:42
 */
public class File1000 {
    public static void main(String[] args) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/main/resources/1000.dat"))) {
            for (int i = 0; i <= 1000; i++) {
                dos.writeInt(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
