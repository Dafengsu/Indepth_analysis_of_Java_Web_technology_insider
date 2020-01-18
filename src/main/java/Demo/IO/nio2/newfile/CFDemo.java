package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/18 19:07
 */
public class CFDemo {
    public static void main(String[] args) throws IOException
    {
        Files.createFile(Paths.get("test.txt"));
    }
}
