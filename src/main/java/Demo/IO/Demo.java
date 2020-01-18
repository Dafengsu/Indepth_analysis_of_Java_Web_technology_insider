package Demo.IO;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author dafengsu
 * @description: 验证内部类的隐藏Field: this$0指向外部类的引用
 * @date 2020/1/5 14:02
 */
public class Demo {
    public DemoRunnable demoRunnable = new DemoRunnable();
    public class DemoRunnable implements Runnable {

        @Override
        public void run() {

        }
    }

    public static void main(String[] args) throws IOException {
        List<FileSystemProvider> fileSystemProviders = FileSystemProvider.installedProviders();
        for (FileSystemProvider provider : fileSystemProviders) {

        }
    }

    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;
    }
}
