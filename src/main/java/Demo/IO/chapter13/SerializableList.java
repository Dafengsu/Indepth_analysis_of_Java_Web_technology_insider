package Demo.IO.chapter13;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/10 21:21
 */
public class SerializableList extends ArrayList implements Externalizable {

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(size());
        for (Object o : this) {
            out.writeObject(o instanceof Serializable ? o : null);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int elementCount = in.readInt();
        ensureCapacity(elementCount);
        for (int i = elementCount; i > 0; i--) {
            add(in.readObject());
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableList list = new SerializableList();
        list.add("Element 1");
        list.add(9);
        list.add(new URL("https://www.oreilly.com/"));
        list.add(new Socket("www.oreilly.com", 80));
        list.add("Element 5");
        list.add(new Integer(9));
        list.add(new URL("http://www.oreilly.com/"));
        ByteArrayOutputStream bout = new ByteArrayOutputStream( );
        ObjectOutputStream temp = new ObjectOutputStream(bout);
        temp.writeObject(list);
        temp.close();
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream oin = new ObjectInputStream(bin);
        List out = (List) oin.readObject();
        for (Object o : out) {
            System.out.println(o);
        }
    }
}
