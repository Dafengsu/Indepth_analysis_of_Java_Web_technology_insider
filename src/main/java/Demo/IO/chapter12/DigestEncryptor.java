package Demo.IO.chapter12;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/8 22:03
 */
public class DigestEncryptor {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java DigestEncryptor filename password ");
            return;
        }
        String filename = args[0];
        String password = args[1];
        if (password.length() < 8) {
            System.err.println("Password must be at least eight characters long");
        }


        try (FileInputStream fin = new FileInputStream(filename);
             FileOutputStream fout = new FileOutputStream(filename + ".des");
             FileOutputStream digest = new FileOutputStream(filename + ".des.digest")) {
            byte[] desKeyData = password.getBytes();
            DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = keyFactory.generateSecret(desKeySpec);

            Cipher des = Cipher.getInstance("DES/ECB/PKCS5Padding");
            des.init(Cipher.ENCRYPT_MODE, desKey);
            CipherInputStream cin = new CipherInputStream(fin, des);

            MessageDigest sha = MessageDigest.getInstance("SHA");
            DigestInputStream din = new DigestInputStream(cin, sha);
            byte[] input = new byte[64];
            while (true) {
                int bytesRead = din.read(input);
                if (bytesRead == -1) {
                    break;
                }
                fout.write(input, 0, bytesRead);
            }
            digest.write(sha.digest());
            digest.close();
            din.close();
            cin.close();
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException e) {
            e.printStackTrace();

        };
    }
}
