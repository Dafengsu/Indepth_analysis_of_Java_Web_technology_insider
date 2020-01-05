package Demo.nio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BackSlashes {
    public static void main(String[] args) {
        String rep = "a\\\\b";
        String input = "> XYZ <=> ABC <";
        Pattern pattern = Pattern.compile("ABC|XYZ");
        Matcher matcher = pattern.matcher(input);

        System.out.println(matcher.replaceFirst(rep));
        System.out.println(matcher.replaceAll(rep));
        
        rep = "\\\\r\\\\n";
        input = "line a\nline 2\nline 3\n";
        pattern = Pattern.compile("\\n");
        matcher = pattern.matcher(input);

        System.out.println();
        System.out.println("Before:");
        System.out.println(input);

        System.out.println("After (dos-ified, escaped):");
        System.out.println(matcher.replaceAll(rep));
        matcher.appendTail(new StringBuffer());
    }
}
