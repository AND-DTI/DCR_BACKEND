package com.dcr.api.configs.security;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.BadPaddingException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SealedObject;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import java.security.Key;


public class AESUtil {

    public static String encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
        InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
            .encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
        InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
            .decode(cipherText));
        return new String(plainText);
    }

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
            .getEncoded(), "AES");
        return secret;
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static void encryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
        File inputFile, File outputFile) throws IOException, NoSuchPaddingException,
        NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
        BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        byte[] buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                outputStream.write(output);
            }
        }
        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) {
            outputStream.write(outputBytes);
        }
        inputStream.close();
        outputStream.close();
    }

    public static void decryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
        File encryptedFile, File decryptedFile) throws IOException, NoSuchPaddingException,
        NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
        BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        FileInputStream inputStream = new FileInputStream(encryptedFile);
        FileOutputStream outputStream = new FileOutputStream(decryptedFile);
        byte[] buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                outputStream.write(output);
            }
        }
        byte[] output = cipher.doFinal();
        if (output != null) {
            outputStream.write(output);
        }
        inputStream.close();
        outputStream.close();
    }

    public static SealedObject encryptObject(String algorithm, Serializable object, SecretKey key,
        IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
        InvalidAlgorithmParameterException, InvalidKeyException, IOException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        SealedObject sealedObject = new SealedObject(object, cipher);
        return sealedObject;
    }

    public static Serializable decryptObject(String algorithm, SealedObject sealedObject, SecretKey key,
        IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
        InvalidAlgorithmParameterException, InvalidKeyException, ClassNotFoundException,
        BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        Serializable unsealObject = (Serializable) sealedObject.getObject(cipher);
        return unsealObject;
    }

    public static String encryptPasswordBased(String plainText, SecretKey key, IvParameterSpec iv)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
        InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return Base64.getEncoder()
            .encodeToString(cipher.doFinal(plainText.getBytes()));
    }

    public static String decryptPasswordBased(String cipherText, SecretKey key, IvParameterSpec iv)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
        InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return new String(cipher.doFinal(Base64.getDecoder()
            .decode(cipherText)));
    }




    public static String decrypt2(String str,String k) throws Exception {
        // Decode base64 to get bytes
        
         Cipher  dcipher = Cipher.getInstance("AES");
         Key aesKey = new SecretKeySpec(k.getBytes(), "AES");
         //dcipher.init(dcipher.DECRYPT_MODE, aesKey);
         dcipher.init(Cipher.DECRYPT_MODE, aesKey); //@@@
        //System.out.println(aesKey);
        //byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
        byte[] dec = Base64.getDecoder().decode(str);
        byte[] utf8 = dcipher.doFinal(dec);
        //System.out.println(utf8);
        // Decode using utf-8
         return new String(utf8, "UTF8");
         }
        
        
    public static String encrypt2(String str, String k) throws Exception {
        
        Cipher ecipher = Cipher.getInstance("AES");
        Key aeskey = new SecretKeySpec(k.getBytes(),"AES");
        byte[] utf8 = str.getBytes("UTF8");
        ecipher.init(Cipher.ENCRYPT_MODE, aeskey );
    
        byte[] enc = ecipher.doFinal(utf8);
    
        //return new sun.misc.BASE64Encoder().encode(enc);
        return Base64.getEncoder().encode(enc).toString();
    
    }


    public static String encrypt2(String str, String k, String Algo) throws Exception {
        
        Cipher ecipher = Cipher.getInstance(Algo);
        Key aeskey = new SecretKeySpec(k.getBytes(),Algo);
        byte[] utf8 = str.getBytes("UTF8");
        ecipher.init(Cipher.ENCRYPT_MODE, aeskey );
    
        byte[] enc = ecipher.doFinal(utf8);
    
        //return new sun.misc.BASE64Encoder().encode(enc);
        return Base64.getEncoder().encode(enc).toString();
    
    }

    public static String decrypt2(String str, String k, String Algo) throws Exception {
    // Decode base64 to get bytes

        Cipher  dcipher = Cipher.getInstance(Algo);
        Key aesKey = new SecretKeySpec(k.getBytes(), Algo);
        dcipher.init(Cipher.DECRYPT_MODE, aesKey);

        //System.out.println(aesKey);
        //byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
        byte[] dec = Base64.getDecoder().decode(str);
        byte[] utf8 = dcipher.doFinal(dec);
        //System.out.println(utf8);
        // Decode using utf-8
            return new String(utf8, "UTF8");
    }
        
    /*public static void main(String args []) throws Exception
    {
        String original = "rakesh";
        String data = "CfPcX0G+e7TLKKMyyvrvrQ==";
        String k = "qertyuiopasdfghw"; //AES key length must be 16
        String k1 = "qertyuio";  // DES key length must be 8 
        String data1 = "rakesh";
        String data2 = "nAtvNq7uHKE=";
        String Algo= "DES";
        String Algo1= "AES";
        Decrypt decrypter = new Decrypt();
            System.out.println("Original String: " + original);
    
            System.out.println("encrypted String in DES: " + decrypter.encrypt(data1, 
            k1,Algo));
            System.out.println("Decrypted String in DES: " + decrypter.decrypt(data2, 
            k1,Algo));
            System.out.println("encrypted String in AES: " + decrypter.encrypt(data1, 
            k,Algo1));
            System.out.println("Decrypted String in AES: " + decrypter.decrypt(data, 
            k,Algo1));
        }
    }*/






}
