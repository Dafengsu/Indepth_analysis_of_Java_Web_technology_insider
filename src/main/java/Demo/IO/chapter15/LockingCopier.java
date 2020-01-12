package Demo.IO.chapter15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/12 21:27
 */
public class LockingCopier {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java LockingCopier SrcFile TarFile");
            return;
        }
        FileChannel inChannel = new FileInputStream(args[0]).getChannel();
        FileChannel outChannel = new FileOutputStream(args[1]).getChannel();
        inChannel.lock(0, inChannel.size(), true);
        outChannel.lock();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inChannel.close();
        outChannel.close();
    }
}
