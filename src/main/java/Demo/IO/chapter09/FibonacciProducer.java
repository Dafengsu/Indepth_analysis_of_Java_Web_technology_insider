package Demo.IO.chapter09;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 11:24
 */
public class FibonacciProducer extends Thread {
    private DataOutputStream theOutput;
    private int howMany;

    public FibonacciProducer(OutputStream out, int howMany) {
        theOutput = new DataOutputStream(out);
        this.howMany = howMany;
    }

    @Override
    public void run() {
        try {
            int f1 = 1;
            int f2 = 1;
            theOutput.writeInt(f1);
            theOutput.writeInt(f2);
            for (int i = 2; i < howMany; i++) {
                int temp = f2;
                f2 += f1;
                f1 = temp;
                if (f2 < 0) {
                    break;
                }
                theOutput.writeInt(f2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
