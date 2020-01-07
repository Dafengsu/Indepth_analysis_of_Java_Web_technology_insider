package Demo.IO.Chapter08;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 8:37
 */
public class DoubleReader {
    public static void main(String[] args) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(args[0]))) {
            System.out.println("------------------" + args[0] + "------------------");
            while (true) {
                float readFloat = dis.readFloat();
                dis.readUTF();
                System.out.println(readFloat);
            }
        } catch (EOFException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        };
    }
}
