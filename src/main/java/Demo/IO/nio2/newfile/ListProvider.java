package Demo.IO.nio2.newfile;

import java.nio.file.spi.FileSystemProvider;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/17 17:48
 */
public class ListProvider {
    public static void main(String[] args) {
        FileSystemProvider.installedProviders()
                .forEach(System.out::println);
    }
}
