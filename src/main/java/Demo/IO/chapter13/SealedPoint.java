package Demo.IO.chapter13;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/11 8:11
 */
public class SealedPoint {
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException {
        Point tdp = new Point(32, 45);
        FileOutputStream fout = new FileOutputStream("src/main/resources/point.des");
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        byte[] desKeyData = {(byte) 0x90, (byte) 0x67, (byte) 0x3E, (byte) 0xE6,
                (byte) 0x42, (byte) 0x15, (byte) 0x7A, (byte) 0xA3 };
        DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = keyFactory.generateSecret(desKeySpec);
        Cipher des = Cipher.getInstance("DES/ECB/PKCS5Padding");
        des.init(Cipher.ENCRYPT_MODE, desKey);
        SealedObject so = new SealedObject(tdp, des);
        oout.writeObject(so);
        oout.close();
    }
}
