package Demo.nio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGrep {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: regex file [...]");
            return;
        }
        Pattern pattern = Pattern.compile(args[0]);
        Matcher matcher = pattern.matcher("");
        for (int i = 1; i < args.length; i++) {
            String file = args[i];
            BufferedReader br = null;
            String line;

            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                System.out.println("Cannot read '" + file + "': " + e.getMessage());
                continue;
            }

            while ((line = br.readLine()) != null) {
                matcher.reset(line);
                if (matcher.find()) {
                    System.out.println(file + ": " + line);
                }
            }
            br.close();
        }
    }
}
