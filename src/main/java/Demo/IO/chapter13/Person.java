package Demo.IO.chapter13;

import org.apache.commons.io.input.BOMInputStream;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/10 21:54
 */
public class Person implements Serializable, ObjectInputValidation {
    static Map<String, String> thePeople = new HashMap<>();
    private String name;
    private String ss;

    public Person(String name, String ss) {
        this.name = name;
        this.ss = ss;
        thePeople.put(ss, name);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.registerValidation(this, 5);
        in.defaultReadObject();
    }

    @Override
    public void validateObject() throws InvalidObjectException {
        if (thePeople.containsKey(ss)) {
            throw new InvalidObjectException(name + " already exists");
        } else {
            thePeople.put(ss, name);
        }
    }

    @Override
    public String toString() {
        return name + "\t" + ss;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person p1 = new Person("Rusty", "123-45-5678");
        Person p2 = new Person("Beth", "321-45-5678");
        Person p3 = new Person("David", "453-45-5678");
        Person p4 = new Person("David", "453-45-5678");
        for (String ss : thePeople.values()) {
            System.out.println(ss);
        }
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(bout);
        oout.writeObject(p1);
        oout.writeObject(p2);
        oout.writeObject(p3);
        oout.writeObject(p4);
        oout.flush();
        oout.close();
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream oin = new ObjectInputStream(bin);
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(oin.readObject());
            }
        } catch (InvalidObjectException e) {
            System.err.println(e);
        }
        oin.close();
        thePeople.clear();
        bin = new ByteArrayInputStream(bout.toByteArray());
        oin = new ObjectInputStream(bin);
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(oin.readObject());
            }
        } catch (InvalidObjectException e) {
            System.err.println(e);
        }
        oin.close();
        for (String ss : thePeople.values()) {
            System.out.println(ss);
        }
    }
}
