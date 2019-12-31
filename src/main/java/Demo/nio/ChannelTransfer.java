package Demo.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelTransfer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: filename....");
            return;
        }
        catFiles(Channels.newChannel(System.out), args);
    }

    private static void catFiles(WritableByteChannel target, String[] files) throws IOException {
        for (String file : files) {
            FileInputStream fis = new FileInputStream(file);
            FileChannel channel = fis.getChannel();

            channel.transferTo(0, channel.size(), target);

            channel.close();
            fis.close();
        }
    }
}
