package Demo.IO.chapter17;

import java.io.File;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 14:30
 */
public class CWDSpace {
    public static void main(String[] args) {
        File cwd = new File(".");
        System.out.println("Total space on current partition: "
                + cwd.getTotalSpace() / (1024 * 1024) + " MB\t");
        System.out.println("Free space on current partition: "
                + cwd.getFreeSpace( ) / (1024 * 1024) + " MB\t");
        System.out.println("Usable space on current partition: "
                + cwd.getUsableSpace( ) / (1024 * 1024) + " MB");
    }
}
