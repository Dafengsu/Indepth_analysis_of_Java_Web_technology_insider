package Demo.IO.chapter02;

/**
 * @author dafengsu
 * @description:    打印Ascii
 * @date 2020/1/5 9:43
 */
public class AsciiChart {
    public static void main(String[] args) {
        for (int i = 32; i < 127; i++) {
            System.out.write(i);
            if (i % 8 == 7) {
                System.out.write(10);
            } else {
                System.out.write(9);
            }
            System.out.flush();
        }
        System.out.write(10);
    }
}
