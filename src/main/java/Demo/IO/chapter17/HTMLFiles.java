package Demo.IO.chapter17;

import java.io.File;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 16:48
 */
public class HTMLFiles {
    public static void main(String[] args) {
        File cwd = new File(System.getProperty("user.dir"));
        File[] htmlFiles = cwd.listFiles(new HTMLFileFilter());
        assert htmlFiles != null;
        for (File htmlFile : htmlFiles) {
            System.out.println(htmlFile);
        }
    }
}
