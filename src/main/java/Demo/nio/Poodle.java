package Demo.nio;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Poodle {
    private static PrintStream out;
    public static void main(String[] args) throws IOException {
        String input = "poodle zoo";
        Pattern space = Pattern.compile(" ");
        Pattern d = Pattern.compile("d");
        Pattern o = Pattern.compile("o");
        Pattern[] patterns = {space, d, o};
        int[] limits = {1, 2, 5, -2, 0};

        if (args.length != 0) {
            input = args[0];
            patterns = collectPatterns(args);
        }
        out = new PrintStream(new BufferedOutputStream(new FileOutputStream("pattern.xml")));
        generateTable(input, patterns, limits);
        out.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("pattern.xml")));
        StringBuilder stringBuilder = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            stringBuilder.append(str);
        }
        String htmlString = XMLTransferHtml.getHTMLString(stringBuilder.toString(), "src/main/resources/Split.xsl");
        PrintStream out2 = new PrintStream(new BufferedOutputStream(new FileOutputStream("pattern.html")));
        out2.print(htmlString);
        out2.flush();
    }

    private static void generateTable(String input, Pattern[] patterns, int[] limits) {
        out.println("<?xml version='1.0'?>");
        out.println("<table>");
        out.println("\t<row>");
        out.println("\t\t<head>Input: " + input + "</head>");
        for (Pattern pattern : patterns) {
            out.println("\t\t<head>Regex: <value>" + pattern.pattern() + "</value></head>");
        }
        out.println("\t</row>");
        for (int limit : limits) {
            out.println("\t<row>");
            out.println("\t\t<entry>Limit: " + limit + "</entry>");
            for (Pattern pattern : patterns) {
                String[] tokens = pattern.split(input, limit);
                out.println("\t\t<entry>");
                for (String token : tokens) {
                    out.println("<value>" + token + "</value>");
                }
                out.println("</entry>");
            }
            out.println("\t</row>");
        }
        out.println("</table>");
    }

    private static Pattern[] collectPatterns(String[] args) {
        List<Pattern> list = new LinkedList<>();
        for (String arg : args) {
            list.add(Pattern.compile(arg));
        }
        Pattern[] patterns = new Pattern[list.size()];
        list.toArray(patterns);
        return patterns;
    }
}
