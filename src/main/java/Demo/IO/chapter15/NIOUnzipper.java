package Demo.IO.chapter15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.GZIPInputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/13 11:37
 */
public class NIOUnzipper {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream(args[0]);

        GZIPInputStream gin = new GZIPInputStream(fin);
        ReadableByteChannel in = Channels.newChannel(gin);
        InputStream inputStream = Channels.newInputStream(in);
        WritableByteChannel out = Channels.newChannel(System.out);
        ByteBuffer buffer = ByteBuffer.allocateDirect(65536);
        while (in.read(buffer) != -1) {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
    }
}
