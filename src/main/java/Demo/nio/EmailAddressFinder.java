package Demo.nio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAddressFinder {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("usage: emailAddress...");
        }
        Pattern pattern = Pattern.compile("([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]"
                        + "{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))"
                        + "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)",
                Pattern.MULTILINE);

        Matcher matcher = pattern.matcher("");

        for (String arg : args) {
            boolean matched = false;

            System.out.println("");
            System.out.println("looking at " + arg + "...");
            matcher.reset(arg);
            while (matcher.find()) {
                System.out.println("\t"+matcher.group());
                matched = true;
            }
            if (!matched) {
                System.out.println("\tNo email address found");
            }
        }

    }
}
