package Demo.IO.chapter11;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.zip.GZIPInputStream;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/8 10:16
 */
public class Unpacker200 {
    public static void main(String[] args) {
        String inName = args[0];
        boolean isGZ = inName.endsWith(".pack.gz");
        String outName = inName.substring(0, inName.length() - (isGZ ? 8 : 5));
        Pack200.Unpacker unpacker = Pack200.newUnpacker();
        try (JarOutputStream out = new JarOutputStream(new FileOutputStream(outName));
             InputStream in = isGZ ? new GZIPInputStream(new FileInputStream(inName)) : new FileInputStream(inName)
        ) {
            unpacker.unpack(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
