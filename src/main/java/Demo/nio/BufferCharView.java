package Demo.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class BufferCharView {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(7).order(ByteOrder./*BIG_ENDIAN*/LITTLE_ENDIAN);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        byteBuffer.put(1, (byte) 0);
        byteBuffer.put(0, (byte) 'H');
        byteBuffer.put(3, (byte) 0);
        byteBuffer.put(2, (byte) 'i');
        byteBuffer.put(5, (byte) 0);
        byteBuffer.put(4, (byte) '!');
        byteBuffer.put(6, (byte) 0);

        println(byteBuffer);
        println(charBuffer);
    }

    private static void println(Buffer buffer) {
        System.out.println("pos=" + buffer.position()
                + ", limit=" + buffer.limit()
                + ", capacity=" + buffer.capacity()
                + ": '" + buffer.toString() + "'");
    }
}
