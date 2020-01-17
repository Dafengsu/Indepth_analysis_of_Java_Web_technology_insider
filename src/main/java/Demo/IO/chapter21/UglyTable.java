package Demo.IO.chapter21;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/17 12:56
 */
public class UglyTable {
    public static void main(String[] args) {
        System.out.println("Degrees \tRadians \tGrads");
        for (double degrees = 0.0; degrees < 360.0; degrees++) {
            double radians = Math.PI * degrees / 180.0;
            double grads = 400 * degrees / 360;
            System.out.println(degrees + "\t" + radians + "\t" + grads);
        }
    }
}
