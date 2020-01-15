package Demo.IO.chapter17;

import java.io.File;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 11:53
 */
public class Paths {
    public static void main(String[] args) {
        File absolute = new File("/public/html/javafaq/index.html");
        File relative = new File("html/javafaq/index.html");
        System.out.println("absolute: ");
        System.out.println(absolute.getName( ));
        System.out.println(absolute.getPath( ));
        System.out.println("relative: ");
        System.out.println(relative.getName( ));
        System.out.println(relative.getPath( ));
    }
}
