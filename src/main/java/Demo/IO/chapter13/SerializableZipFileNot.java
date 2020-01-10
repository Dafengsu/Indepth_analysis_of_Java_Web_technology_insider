package Demo.IO.chapter13;

import java.io.*;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/9 21:59
 */
public class SerializableZipFileNot extends ZipFile implements Serializable{

    public SerializableZipFileNot(String name) throws IOException {
        super(name);
    }

    public SerializableZipFileNot(File file) throws ZipException, IOException {
        super(file);
    }

    public static void main(String[] args) {
        try {
            SerializableZipFileNot szf = new SerializableZipFileNot(args[0]);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(bout);
            oout.writeObject(szf);
            oout.close();
            System.out.println("Write Object");
            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream oin = new ObjectInputStream(bin);
            Object o = oin.readObject();
            System.out.println("Read Object");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
