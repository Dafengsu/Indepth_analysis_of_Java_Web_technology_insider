package Demo.IO.chapter09;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 11:04
 */
public class FibonacciFile {
    public static void main(String[] args) throws IOException {
        int howMany = 20;
        ByteArrayOutputStream bout = new ByteArrayOutputStream(howMany * 4);
        DataOutputStream dout = new DataOutputStream(bout);
        int f1 = 1;
        int f2 = 1;
        dout.writeInt(f1);
        dout.writeInt(f2);
        for (int i = 3; i < 20; i++) {
            int temp = f2;
            f2 = f2 + f1;
            f1 = temp;
            dout.writeInt(f2);
        }
        try (FileOutputStream fout = new FileOutputStream("src/main/resources/fibonacci.dat")) {
            bout.writeTo(fout);
            fout.flush();
        }
    }
}
