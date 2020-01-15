package Demo.IO.chapter16;

import java.io.IOException;
import java.nio.channels.Pipe;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/13 19:20
 */
public class NewIOFibonacciDriver {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel out = pipe.sink();
        Pipe.SourceChannel in = pipe.source();
        FibonacciProducer producer = new FibonacciProducer(out, 200);
        FibonacciConsumer consumer = new FibonacciConsumer(in);
        producer.start( );
        consumer.start( );
    }
}
