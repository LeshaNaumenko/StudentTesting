package utility;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Class {@code EncryptionBuilder} is a builder which contains hash and salt for a specific password.
 *
 * @author Alex Naumenko
 */
public class EncryptionBuilder {
    /**
     * Hash of some string
     */

    private String hash;
    /**
     * Salt of some string
     */
    private byte[] salt;

    /**
     * Constructs builder that receives a password as a parameter and determines
     * the user's hash and salt according to the password provided.
     *
     * @param password
     *
     * @see utility.Encryption;
     */
    public EncryptionBuilder(String password) {
        try {
            this.salt = Encryption.getSalt();
            this.hash = Encryption.getSecurePassword(password, salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a hash
     *
     * @return a hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * Returns a salt
     *
     * @return a salt
     */
    public byte[] getSalt() {
        return salt;
    }
}
