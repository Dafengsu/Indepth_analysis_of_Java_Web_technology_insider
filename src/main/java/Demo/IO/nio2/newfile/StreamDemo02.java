package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/19 20:42
 */
public class StreamDemo02 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1)
        {
            System.err.println("usage: java StreamsDemo textfilepath");
            return;
        }
        Files.lines(Paths.get(args[0])).forEach(System.out::println);
    }
}
