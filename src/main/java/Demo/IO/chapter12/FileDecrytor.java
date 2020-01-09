package Demo.IO.chapter12;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/8 20:05
 */
public class FileDecrytor {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java FileDEcryptor infile outfile password");
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
             DataInputStream din = new DataInputStream(fin)) {
            //create key
            byte[] desKeyData = password.getBytes();
            DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = keyFactory.generateSecret(desKeySpec);
            //read  the initialization vector
            int ivSize = din.readInt();
            byte[] iv = new byte[ivSize];
            din.readFully(iv);
            IvParameterSpec ivps = new IvParameterSpec(iv);
            //Use Data Encryption Standard
            Cipher des = Cipher.getInstance("DES/CBC/PKCS5Padding");
            des.init(Cipher.DECRYPT_MODE, desKey, ivps);
            byte[] input = new byte[64];

            //process data
            processData(fin, fout, input, des);

        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public static void processData(InputStream in, OutputStream out, byte[] buff, Cipher cipher) throws IOException, BadPaddingException, IllegalBlockSizeException {
        while (true) {
            int bytesRead = in.read(buff);
            if (bytesRead == -1) {
                break;
            }
            byte[] output = cipher.update(buff, 0, bytesRead);
            if (output != null) {
                out.write(output);
            }
        }
        byte[] output = cipher.doFinal();
        if (output != null) {
            out.write(output);
        }
        out.close();
    }
}
