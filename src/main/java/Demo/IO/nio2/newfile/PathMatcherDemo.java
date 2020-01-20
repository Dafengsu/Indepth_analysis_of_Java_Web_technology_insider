package Demo.IO.nio2.newfile;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/20 10:48
 */
public class PathMatcherDemo {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("usage: java PatchMatcherDemo syntax:pattern path");
            return;
        }
        FileSystem fileSystem = FileSystems.getDefault();
        PathMatcher pm = fileSystem.getPathMatcher(args[0]);
        if (pm.matches(fileSystem.getPath(args[1]))) {
            System.out.println(args[1] + " matches pattern");
        } else {
            System.out.println(args[1] + " doesn't matches pattern");
        }
    }
}
