package Demo.IO.chapter10;

import java.util.zip.Checksum;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/7 21:36
 */
public class ParityChecksum implements Checksum {
    private long checksum = 0;
    @Override
    public void update(int b) {
        int mumOneBits = 0;
        for (int i = 1; i < 256; i *= 2) {
            if ((b & i) != 0) {
                mumOneBits++;
            }
        }
        checksum = (checksum + mumOneBits) % 2;
    }

    @Override
    public void update(byte[] b, int off, int len) {
        for (int i = off; i < off + len; i++) {
            update(b[i]);
        }
    }

    @Override
    public long getValue() {
        return checksum;
    }

    @Override
    public void reset() {
        checksum = 0;
    }
}
