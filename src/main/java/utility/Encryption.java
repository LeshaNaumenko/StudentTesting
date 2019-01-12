package utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * Class {@code Encryption} contains methods for working with encryption.
 *
 * @author Alex Naumenko
 */
public class Encryption {

    /**
     * The method compares the password entered by the user with the encrypted one.
     *
     * @param providedPassword
     * @param securedPassword
     * @param salt
     * @return verified
     */
    public static boolean verifyUserPassword(String providedPassword, String securedPassword, byte[] salt) {
        boolean verified = false;

        String newSecurePassword = getSecurePassword(providedPassword, salt);
        verified = newSecurePassword.equalsIgnoreCase(securedPassword);

        return verified;
    }

    /**
     * The method generates a hash using the MD5 algorithm in accordance with the provided password and salt.
     *
     * @param passwordToHash
     * @param salt
     * @return generated hash
     */
    public static String getSecurePassword(String passwordToHash, byte[] salt) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    /**
     * The method generates a salt using {@code SecureRandom} class.
     *
     * @return generated salt
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
