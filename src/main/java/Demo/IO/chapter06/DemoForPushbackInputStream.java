package Demo.IO.chapter06;

import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 21:35
 */
public class DemoForPushbackInputStream {
    public static void main(String[] args) throws IOException {
        PushbackInputStream in = new PushbackInputStream(System.in, 5);
        in.unread(0);
        in.unread(1);
        in.unread(2);
        System.out.println(in.read());
        System.out.println(in.read());
        System.out.println(in.read());
    }
}
