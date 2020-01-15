package Demo.IO;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author dafengsu
 * @description: 验证内部类的隐藏Field: this$0指向外部类的引用
 * @date 2020/1/5 14:02
 */
public class Demo {
    public DemoRunnable demoRunnable = new DemoRunnable();
    public class DemoRunnable implements Runnable {

        @Override
        public void run() {

        }
    }

    public static void main(String[] args) throws IOException {
        byte[] isoLatin1 = new byte[1];
        for (int i = 129; i < 130; i++) {
            isoLatin1[0] = (byte) i;
        }

        System.setProperty("file.encoding", "ISO-8859-1");
        String s = new String(isoLatin1);
        System.out.println(s);
        System.out.println(System.getProperty("file.encoding"));
    }

    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;

    }
}
