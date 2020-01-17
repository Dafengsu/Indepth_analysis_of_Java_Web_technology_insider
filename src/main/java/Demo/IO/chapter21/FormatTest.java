package Demo.IO.chapter21;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/17 12:42
 */
public class FormatTest {
    public static void main(String[] args) {
        NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
        for (double x = Math.PI; x < 100000; x *= 10) {
            String format = nf.format(x);
            System.out.println(format + "\t" + x);
        }
    }
}
