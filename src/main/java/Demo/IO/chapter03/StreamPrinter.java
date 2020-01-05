package Demo.IO.chapter03;

import java.io.IOException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 10:53
 */
public class StreamPrinter {
    public static void main(String[] args) {
        try {
            while (true) {
                int datum = System.in.read();
                if (datum == -1) {
                    break;
                }
                System.out.println(datum);
            }
        } catch (IOException e) {
            System.err.println("Couldn't read from System.in!");
        }
    }
}
