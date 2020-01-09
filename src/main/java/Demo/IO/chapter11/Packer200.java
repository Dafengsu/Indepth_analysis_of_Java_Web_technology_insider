package Demo.IO.chapter11;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.jar.JarFile;
import java.util.jar.Pack200;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/8 10:08
 */
public class Packer200 {
    public static void main(String[] args) {
        try (OutputStream out = new FileOutputStream(args[0] + ".pack")) {
            JarFile f = new JarFile(args[0]);
            Pack200.Packer packer = Pack200.newPacker();
            packer.pack(f, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
