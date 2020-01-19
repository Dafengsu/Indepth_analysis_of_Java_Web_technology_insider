package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/19 20:30
 */
public class StreamDemo {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("usage: java StreamDemo dirPath ext");
            return;
        }
        BiPredicate<Path, BasicFileAttributes> predicate = (path, attrs)
                -> attrs.isRegularFile() && path.getFileName().toString().endsWith(args[1]);
        try (Stream<Path> stream = Files.find(Paths.get(args[0]), 1, predicate)) {
            stream.collect(Collectors.toList()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }
}
