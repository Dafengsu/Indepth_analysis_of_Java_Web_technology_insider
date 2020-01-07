package Demo.IO.Chapter08;

import java.io.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 9:00
 */
public class UTFTest {
    public static void main(String[] args) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/main/resources/string.dat"));
             DataInputStream dis = new DataInputStream(new FileInputStream("src/main/resources/string.dat"))) {

            dos.writeUTF("测试一下");
            dos.writeFloat(2);

            String s = dis.readUTF();
            System.out.println(s);
        } catch (IOException e) {
        };
    }
}
