package de.klickreform.dropkit.utils;

import java.security.NoSuchAlgorithmException;

/**
 * Created by benjamin on 17.06.15.
 */
public class HashGenerator {

    private String secret;

    public HashGenerator(String secret) {
        this.secret = secret;
    }

    public String hash(String value) throws NoSuchAlgorithmException {
        return ShaUtils.encryptStringSha256(value+secret);
    }

    public boolean check(String value, String hash) throws NoSuchAlgorithmException {
        if(ShaUtils.encryptStringSha256(value+secret).equals(hash)) {
            return true;
        } else {
            return false;
        }
    }

}
