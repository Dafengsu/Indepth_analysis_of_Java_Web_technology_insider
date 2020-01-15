package Demo.IO.chapter17;

import java.io.File;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 11:43
 */
public class RootLister {
    public static void main(String[] args) {
        File[] files = File.listRoots();
        for (File file : files) {
            System.out.println(file);
        }
    }
}
