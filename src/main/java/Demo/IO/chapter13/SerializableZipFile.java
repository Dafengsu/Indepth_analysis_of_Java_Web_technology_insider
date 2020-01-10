package Demo.IO.chapter13;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/10 17:38
 */
public class SerializableZipFile implements Serializable {
    private ZipFile zf;

    public SerializableZipFile(String filename) throws IOException {
        zf = new ZipFile(filename);
    }

    public SerializableZipFile(File file) throws IOException {
        zf = new ZipFile(file);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(zf.getName());
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        zf = new ZipFile((String) in.readObject());
    }

    public ZipEntry getEntry(String name) {
        return zf.getEntry(name);
    }

    public InputStream getInputStream(ZipEntry entry) throws IOException {
        return zf.getInputStream(entry);
    }

    public String getName() {
        return zf.getName();
    }

    public Enumeration<? extends ZipEntry> entries() {
        return zf.entries();
    }

    public int size() {
        return zf.size();
    }

    public void close() throws IOException {
        zf.close();
    }

    public static void main(String[] args) {
        try {
            SerializableZipFile szf = new SerializableZipFile(args[0]);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(bout);
            oout.writeObject(szf);
            oout.close();
            System.out.println("Write object");
            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream oin = new ObjectInputStream(bin);
            Object o = oin.readObject();
            System.out.println("Read Object");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
