package com.techflow.ws.sys.domain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/*
 * Tamanho da chave AES: 16, 24 ou 32 bytes
 * InitVector: 16 bytes

 */
public class EncryptUtils {
	
    /*
	public static String md5(String str) {
		String hash = DigestUtils.md5(str).toString();
		return hash;
	}
    */
	
	public static String getMD5(String input) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Add the bytes of the input string to the digest
            md.update(input.getBytes());
            
            // Get the hash's bytes
            byte[] bytes = md.digest();
            
            // This will contain hash as hexadecimal
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            // Return the complete hash
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}