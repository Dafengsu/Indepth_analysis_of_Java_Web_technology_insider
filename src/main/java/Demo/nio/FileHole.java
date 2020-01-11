package Demo.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileHole {
    public static void main(String[] args) throws IOException {
        File tempFile = File.createTempFile("holy", null);
        RandomAccessFile file = new RandomAccessFile(tempFile, "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);

        putData(0, byteBuffer, channel);
        putData(2000000000, byteBuffer, channel);
        putData(50000, byteBuffer, channel);

        System.out.println ("Wrote temp file '" + tempFile.getPath( )
                + "', size=" + channel.size( ));
        channel.close( );
        file.close( );

    }

    private static void putData(int position, ByteBuffer buffer, FileChannel channel) throws IOException {
        String string = "*<--location" + position;

        buffer.clear();
        buffer.put(string.getBytes(StandardCharsets.US_ASCII));
        buffer.flip();

        channel.position(position);
        channel.write(buffer);
    }
}
