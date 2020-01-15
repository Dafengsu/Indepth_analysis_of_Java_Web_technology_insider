package Demo.IO.chapter17;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 16:34
 */
public class HTMLFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(".html") || name.endsWith(".htm");
    }
}
