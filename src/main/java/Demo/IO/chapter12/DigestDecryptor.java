package Demo.IO.chapter12;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/9 14:44
 */
public class DigestDecryptor {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java DigestDecryptor infile outfile password");
            return;
        }
        String infile = args[0];
        String outfile = args[1];
        String password = args[2];
        if (password.length() < 8) {
            System.err.println("Password must be at least eight characters long");
        }
        try (FileInputStream fin = new FileInputStream(infile);
             FileOutputStream fout = new FileOutputStream(outfile);
             FileInputStream digestIn = new FileInputStream(infile+".digest");
             DataInputStream dataIn = new DataInputStream(digestIn)) {
            byte[] oldDigest = new byte[20];
            dataIn.readFully(oldDigest);
            byte[] desKeyData = password.getBytes();
            DESKeySpec desKeySpec = new DESKeySpec(desKeyData);

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = keyFactory.generateSecret(desKeySpec);

            Cipher des = Cipher.getInstance("DES/ECB/PKCS5Padding");
            des.init(Cipher.DECRYPT_MODE, desKey);
            CipherOutputStream cout = new CipherOutputStream(fout, des);

            MessageDigest sha = MessageDigest.getInstance("SHA");
            DigestInputStream din = new DigestInputStream(fin, sha);
            byte[] input = new byte[64];
            while (true) {
                int bytesRead = din.read(input);
                if (bytesRead == -1) {
                    break;
                }
                cout.write(input, 0, bytesRead);
            }
            byte[] newDigest = sha.digest();
            if (!MessageDigest.isEqual(newDigest, oldDigest)) {
                System.out.println("Input file appears to be corrupt!");
            }
            din.close();
            cout.flush();
            cout.close();
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

    }
}
