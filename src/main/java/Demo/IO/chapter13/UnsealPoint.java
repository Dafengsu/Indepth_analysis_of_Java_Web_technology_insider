package Demo.IO.chapter13;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/11 8:21
 */
public class UnsealPoint {
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, ClassNotFoundException {
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream("src/main/resources/point.des"));
        byte[] desKeyData = {(byte) 0x90, (byte) 0x67, (byte) 0x3E, (byte) 0xE6,
                (byte) 0x42, (byte) 0x15, (byte) 0x7A, (byte) 0xA3};
        DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = keyFactory.generateSecret(desKeySpec);

        SealedObject so = (SealedObject) oin.readObject();
        Point p = (Point) so.getObject(desKey);
        System.out.println(p);
        oin.close();
    }
}
