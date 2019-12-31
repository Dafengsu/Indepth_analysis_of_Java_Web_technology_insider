package Demo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class EndiannessTest {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(12);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));

        bb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));

        bb.rewind();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4).put((byte) 'a').put((byte) 'b').order(ByteOrder.LITTLE_ENDIAN);
    }
}
