package org.aptech.on_Tap.DAOPattern.ultis;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class AESUtils {
    private static final int KEY_LENGTH = 256;
    private static final int ITERATION_COUNT = 65536;
    private static final String secretKey  = "eerterterte545t43tedrdgd";
    private static final String salt = "33wre353";

    //    public static String encrypt(String strToEncrypt) {
//
//        try {
//
//            SecureRandom secureRandom = new SecureRandom();
//            byte[] iv = new byte[16];
//            secureRandom.nextBytes(iv);
//            IvParameterSpec ivspec = new IvParameterSpec(iv);
//
//            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
//            SecretKey tmp = factory.generateSecret(spec);
//            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);
//
//            byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
//            byte[] encryptedData = new byte[iv.length + cipherText.length];
//            System.arraycopy(iv, 0, encryptedData, 0, iv.length);
//            System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);
//
//            return Base64.getEncoder().encodeToString(encryptedData);
//        } catch (Exception e) {
//            // Handle the exception properly
//            e.printStackTrace();
//            return null;
//        }
//    }
    public static String encrypt(String strToEncrypt) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = secretKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.err.println(encrypt("admin@123"));
        System.err.println(encrypt("admin@123"));
        System.err.println(encrypt("admin@123"));
    }
}
