package Demo.IO.chapter20;

import java.io.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/16 12:41
 */
public class UnicodeBMPTable {
    public static OutputStream target;
    public static String encoding;
    public static String lineSeparator;
    public static void main(String[] args) throws FileNotFoundException {
        initially(args);
        try (OutputStreamWriter out = new OutputStreamWriter(target, encoding)) {
            writeCharacter(out,lineSeparator);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeCharacter(Writer out,String lineSeparator) throws IOException {
        for (int i = Character.MIN_VALUE; i <= Character.MAX_VALUE; i++) {
            if (!Character.isDefined(i)) continue;
            char c = (char) i;
            if (!Character.isHighSurrogate(c) && !Character.isLowSurrogate(c))
                out.write(i + ":\t" + c + lineSeparator);
        }
    }

    public static void initially(String[] args) throws FileNotFoundException {
        lineSeparator = System.getProperty("line.separator", "\r\n");
        target = args.length > 0 ? new FileOutputStream(args[0]) : System.out;
        encoding = args.length > 1 ? args[1] : System.getProperty("file.encoding", "ISO-8859-1");
    }
}
