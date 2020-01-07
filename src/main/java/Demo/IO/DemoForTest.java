package Demo.IO;

import Demo.IO.Chapter07.FormattableURL;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Formatter;

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


        float v = Float.parseFloat("3.14");
        String s1 = String.valueOf(2);
        System.out.println(v);
        System.out.printf("好的%n你%n");
        URL url = new URL("http://www.example.org/index.html?name=value#Fred");
        System.out.printf("%60.40S\n", new FormattableURL(url));

    }
}
