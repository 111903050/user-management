package com.management.user.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class PasswordUtil {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Method to validate a password
    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
    public static String createPassword(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // You can also use 192 or 256 bits
        return keyGen.generateKey();
    }

    // Encrypt a password
    public static String encrypt(String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt a password
    public static String decrypt(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = generateKey();
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decrypted);
    }

    /*public static String hashPassword(String password) {
        Argon2 argon2 = Argon2Factory.create();
        String hashedPassword = argon2.hash(4, 1024 * 1024, 8, password);
        argon2.wipeArray(password.toCharArray()); // Clear sensitive data
        return hashedPassword;
    }*/

/*    // Verify a password against a stored hash
    public static boolean verifyPassword(String hashedPassword, String password) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hashedPassword, password);
    }

*//*    public static String hashPassword(String password) {
        int N = 16384; // CPU cost parameter
        int r = 8; // Memory cost parameter
        int p = 1; // Parallelization parameter
        return SCryptUtil.scrypt(password, N, r, p);
    }*//*

    // Verify a password
    public static boolean checkPassword(String password, String hashed) {
        return SCryptUtil.check(password, hashed);
    }*/
}
