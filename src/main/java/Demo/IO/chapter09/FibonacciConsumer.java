package Demo.IO.chapter09;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 11:29
 */
public class FibonacciConsumer extends Thread {
    private DataInputStream theInput;

    public FibonacciConsumer(InputStream in) {
        theInput = new DataInputStream(in);
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(theInput.readInt());
            }
        } catch (IOException e) {
            if (e.getMessage().equals("Pip broken")
                    || e.getMessage().equals("Write end dead")) {
                return;
            }
            e.printStackTrace();
        }
    }
}
