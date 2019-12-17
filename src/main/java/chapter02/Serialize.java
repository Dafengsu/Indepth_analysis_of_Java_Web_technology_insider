package chapter02;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.io.*;

public class Serialize implements Serializable {
    private static final long serialVersionUID = -6849794470754660011L;
    public int num = 1390;

    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("src/main/data/serialize.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Serialize serialize = new Serialize();
            oos.writeObject(serialize);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/data/serialize.dat"));
            Serialize o = (Serialize) ois.readObject();
            System.out.println(o.num);
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
