package Demo.IO.chapter21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/17 14:27
 */
public class RootFinder {
    public static void main(String[] args) {
        Number input;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            NumberFormat nf = NumberFormat.getInstance(Locale.US);
            while (true) {
                System.out.println("Enter a number (-1 to quit): ");
                String s = br.readLine();
                try {
                    input = nf.parse(s);
                } catch (ParseException e) {
                    System.out.println(s + " is not a number I understand.");
                    continue;
                }
                double d = input.doubleValue();
                if (d<0) break;
                double root = Math.sqrt(d);
                System.out.println("The square root of " + s + " is " + root);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
