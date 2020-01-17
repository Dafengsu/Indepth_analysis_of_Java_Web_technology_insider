package Demo.IO.chapter21;

import java.text.NumberFormat;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/17 12:59
 */
public class PrettyTable {
    public static void main(String[] args) {
        System.out.println("Degrees Radians Grads");
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumIntegerDigits(3);
        format.setMaximumFractionDigits(3);
        format.setMinimumFractionDigits(2);
        for (double degrees = 0.0; degrees < 360.0; degrees++) {
            String radians = format.format(Math.PI * degrees / 180.0);
            String grads = format.format(400 * degrees / 360);
            String degreesStr = format.format(degrees);
            System.out.println(degreesStr + "\t" + radians + "\t" + grads);
        }
    }
}
