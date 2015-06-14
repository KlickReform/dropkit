package de.klickreform.dropkit.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA256 encryption utilities
 *
 * @author Benjamin Bestmann
 */
public class ShaUtils {

    public static String encryptStringSha256(String stringToEncrypt) throws NoSuchAlgorithmException {
        if((stringToEncrypt != null) && (!stringToEncrypt.isEmpty())) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(stringToEncrypt.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } else {
            // If the String to be encrypted is empty, return an empty String
            return null;
        }
    }

}
