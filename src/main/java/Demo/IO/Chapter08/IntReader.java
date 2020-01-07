package Demo.IO.Chapter08;

import java.io.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/6 14:58
 */
public class IntReader {
    public static void main(String[] args) {
        try (DataInputStream din = new DataInputStream(new FileInputStream(args[0]))) {
            System.out.println("--------------" + args[0] + "------------------");
            while (true) {
                int number = din.readInt();
                System.out.println(number);
            }
        } catch (EOFException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
