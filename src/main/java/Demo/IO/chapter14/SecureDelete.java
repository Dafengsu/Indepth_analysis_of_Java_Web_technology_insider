package Demo.IO.chapter14;

import sun.nio.ch.FileChannelImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/11 11:44
 */
public class SecureDelete {
    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        File file = new File(args[0]);
        if (file.exists()) {
            SecureRandom random = new SecureRandom();
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel channel = raf.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, raf.length());
            while (buffer.hasRemaining()) {
                buffer.put((byte) 0);
            }
            buffer.force();
            buffer.rewind();
            while (buffer.hasRemaining()) {
                buffer.put((byte) 0xFF);
            }
            buffer.force();
            buffer.rewind();
            byte[] data = new byte[1];
            while (buffer.hasRemaining()) {
                random.nextBytes(data);
                buffer.put(data[0]);
            }
            buffer.force();
            Method m = FileChannelImpl.class.getDeclaredMethod("unmap", MappedByteBuffer.class);
            m.setAccessible(true);
            m.invoke(FileChannelImpl.class, buffer);
            channel.close();
            raf.close();
            boolean delete = file.delete();
            System.out.println(delete);
        }
    }
}
