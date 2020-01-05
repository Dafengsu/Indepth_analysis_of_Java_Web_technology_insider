package Demo.IO.chapter02;

import java.io.IOException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 9:54
 */
public class AsciiArray {
    public static void main(String[] args) {
        byte[] b = new byte[(127 - 31) * 2];
        int index = 0;
        for (int i = 32; i < 127; i++) {
            b[index++] = (byte) i;
            if (i % 8 == 7) {
                b[index++] = (byte) '\n';
            } else {
                b[index++] = (byte) '\t';
            }
        }
        try {
            System.out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
