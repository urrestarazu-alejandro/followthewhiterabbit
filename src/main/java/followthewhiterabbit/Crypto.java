package followthewhiterabbit;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {
    public String getMD5Hash(String string) {
        //return DigestUtils.md5Hex(string);
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(string.getBytes("UTF-8"));

            return String.format("%032x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }

    }
}
