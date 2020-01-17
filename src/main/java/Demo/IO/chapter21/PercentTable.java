package Demo.IO.chapter21;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/17 13:22
 */
public class PercentTable {
    public static void main(String[] args) {
        NumberFormat format = NumberFormat.getPercentInstance(Locale.US);
        format.setMaximumFractionDigits(3);
        for (double d = 0.0; d <= 1.0; d += 0.005)
            System.out.println(format.format(d));
    }
}
