import java.io.File;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 15:27
 */
public class ProFileName {
    public static void main(String[] args) {
        File file = new File(".");
        File[] files = file.listFiles();
        for (File f : files) {
            f.renameTo(new File(f.getName().replaceAll("]", "")));
        }
    }
}
