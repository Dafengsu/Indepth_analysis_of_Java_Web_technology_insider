package Demo.IO.chapter20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/16 16:48
 */
public class Cat {
    public static void main(String[] args) {
        String thisLine;
        for (String arg : args)
            try (BufferedReader br = new BufferedReader(new FileReader(arg))) {
                while ((thisLine = br.readLine()) != null) {
                    System.out.println(thisLine);
                }
            } catch (IOException ignored) {
            }
    }
}
