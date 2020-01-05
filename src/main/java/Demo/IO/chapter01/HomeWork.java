package Demo.IO.chapter01;

import java.io.Console;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 9:16
 */
public class HomeWork {
    public static void main(String[] args) {
        Console console = System.console();
        String input = console.readLine("Please enter a number between 1 and 10:");
        int max = Integer.parseInt(input);
        for (int i = 1; i < max; i++) {
            console.printf("%d\n", i * i);
        }
    }
}
