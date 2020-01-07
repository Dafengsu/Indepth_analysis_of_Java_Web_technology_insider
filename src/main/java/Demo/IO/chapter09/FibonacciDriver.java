package Demo.IO.chapter09;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 11:33
 */
public class FibonacciDriver {
    public static void main(String[] args) throws IOException {
        PipedOutputStream pout = new PipedOutputStream();
        PipedInputStream pin = new PipedInputStream(pout);

        FibonacciProducer fw = new FibonacciProducer(pout, 20);
        FibonacciConsumer fr = new FibonacciConsumer(pin);
        fw.start();
        fr.start();
    }
}
