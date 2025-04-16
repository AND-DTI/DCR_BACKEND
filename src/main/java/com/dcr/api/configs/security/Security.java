package com.dcr.api.configs.security;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.auth.AuthScope;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;



@Configuration
public class Security {

    //@Value("${data.proxy.host}")
    //static String ENV_PROXY_HOST;
    //@Value("${data.proxy.port}")
    //static int ENV_PROXY_PORT;
    //@Value("${data.api_se.api_gateway}")
    //static String APIGATEWAY_SE;

    @Autowired
    Environment env;


    public static HttpClient configProxyClient(HttpClient client) {

        String PROXY_HOST = "gateway.zscloud.net";
        int PROXY_PORT = 443;

        HostConfiguration config = client.getHostConfiguration();
        config.setProxy(PROXY_HOST, PROXY_PORT);

        AuthScope authScope = new AuthScope(PROXY_HOST, PROXY_PORT);
        client.getState().setProxyCredentials(authScope, null);

        return client;

    }


    public HttpClient configProxyClient2(HttpClient client) {

        String PROXY_HOST = "";
        int PROXY_PORT = 0;

        try {
            PROXY_HOST = env.getProperty("data.proxy.host");
            PROXY_PORT = Integer.valueOf(env.getProperty("data.proxy.port"));

            HostConfiguration config = client.getHostConfiguration();
            config.setProxy(PROXY_HOST, PROXY_PORT);

            AuthScope authScope = new AuthScope(PROXY_HOST, PROXY_PORT);
            client.getState().setProxyCredentials(authScope, null);

        } catch (Exception e) {

        }

        return client;

    }


    public String getAuthenticationHeader(String module) {

        String apiToken = env.getProperty("data.api_se.api_gateway");
        return apiToken;

    }

    
    public static String getDefaultPass(String module) {

        String pass = "";

        if (module.equals("SESUITE")) {
            pass = "Manaus@01";
        }

        return pass;
    }


   
    public static String encrypt_simple(String input) 
        //throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException{
        throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
        InvalidAlgorithmParameterException, NoSuchPaddingException, IOException, BadPaddingException,
        ClassNotFoundException {
        

        SecretKey key = AESUtil.generateKey(128);
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";

        // when
        String cipherText = AESUtil.encrypt(algorithm, input, key, ivParameterSpec);
        //String plainText = AESUtil.decrypt(algorithm, cipherText, key, ivParameterSpec);
        

        return cipherText;

    }

    public static String dencrypt_simple(String input) 
        //throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException{
        throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
        InvalidAlgorithmParameterException, NoSuchPaddingException, IOException, BadPaddingException,
        ClassNotFoundException {
        

        SecretKey key = AESUtil.generateKey(128);
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";

        // when
        String cipherText = input;
        String plainText = AESUtil.decrypt(algorithm, cipherText, key, ivParameterSpec);
        

        return plainText;

    }

    public static String encrypt_default(String input)            
        throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException,
        InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
    
        // given
        String plainText = input;
        //String password = "SUPER_SECRET_HDA"; //get from env config
        String password = "baeldung";
        String salt = "12345678";   //get from env config
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        SecretKey key = AESUtil.getKeyFromPassword(password, salt);

        // when
        String cipherText = AESUtil.encryptPasswordBased(plainText, key, ivParameterSpec);
        //String decryptedCipherText = AESUtil.decryptPasswordBased(cipherText, key, ivParameterSpec);

        return cipherText;

    }

    public static String dencrypt_default(String input)            
        throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException,
        InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        // given
        String cipherText = input;
        //String password = "SUPER_SECRET_HDA"; //get from env config
        String password = "baeldung";
        String salt = "12345678";   //get from env config
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        SecretKey key = AESUtil.getKeyFromPassword(password, salt);

        // when
        String decryptedCipherText = AESUtil.decryptPasswordBased(cipherText, key, ivParameterSpec);

        return decryptedCipherText;

    }

}