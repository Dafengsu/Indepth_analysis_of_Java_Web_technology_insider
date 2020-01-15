package Demo.IO.chapter17;

import java.io.File;
import java.io.FileFilter;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 16:46
 */
public class HTMLFileFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        String name = pathname.getName();
        return name.endsWith(".html") || name.endsWith(".htm");
    }
}
