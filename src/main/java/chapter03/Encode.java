package chapter03;


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @description:
 * @author: su
 * @date: 2020/2/12
 */
public class Encode {
    public static void main(String[] args) {
        String name = "I am 君山";
        toHex(name.getBytes(), Charset.defaultCharset().name());
        try {
            toHex(name, "ISO-8859-1");
            toHex(name, "GB2312");
            toHex(name, "GBK");
            toHex(name, "UTF-8");
            toHex(name, "UTF-16");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    private static void toHex(String name, String encoding) throws UnsupportedEncodingException {
        toHex(name.getBytes(encoding), encoding);
    }
    private static void toHex(byte[] chars, String encoding) {
        System.out.println(Arrays.toString(chars));
        System.out.print(encoding + " : ");
        for (byte ch : chars) {
            int val = ch;
            if (val < 0) {
                val <<= 24;
                val >>>= 24;
            }
            System.out.print(Integer.toHexString(val).toUpperCase()
                    + " ");
        }
        System.out.println();
    }
}
