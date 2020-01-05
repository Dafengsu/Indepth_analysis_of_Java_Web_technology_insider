package Demo.IO;

import java.io.*;

public class DemoForTest {
    public static void main(String[] args) throws IOException {
        byte a = (byte) 127;
        byte b = (byte) 22;
        byte c = (byte) (a + b);
        int d = 1231;
        char f = 65;
        System.out.checkError();
        System.out.println(f);
        String s = "Hello,World";
        System.out.println(s);

        byte[] bytes = s.getBytes();
        System.out.write(bytes);
        InputStream in = System.in;
        int read = in.read();


    }
}
