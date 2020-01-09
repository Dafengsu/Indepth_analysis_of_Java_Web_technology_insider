package Demo.IO.chapter12;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static Demo.IO.chapter12.FileDecrytor.processData;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/8 18:48
 */
public class FileEncryptor {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java FileEncryptor filename password");
            return;
        }
        String filename = args[0];
        String password = args[1];
        if (password.length() < 8) {
            System.err.println("Password must be at least eight characters long");
        }
        try {
            FileInputStream fin = new FileInputStream(filename);
            FileOutputStream fout = new FileOutputStream(filename + ".des");
            //Create a key.
            byte[] desKeyData = password.getBytes();
            DESKeySpec deSedeKeySpec = new DESKeySpec(desKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = keyFactory.generateSecret(deSedeKeySpec);
            //Use Data Encryption Standard
            Cipher des = Cipher.getInstance("DES/CBC/PKCS5Padding");
            des.init(Cipher.ENCRYPT_MODE, desKey);
            //Write the initialization vector onto output
            byte[] iv = des.getIV();
            DataOutputStream dout = new DataOutputStream(fout);
            dout.writeInt(iv.length);
            dout.write(iv);
            byte[] input = new byte[24];
            processData(fin, fout, input, des);

            fin.close();
            dout.close();
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | IOException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
