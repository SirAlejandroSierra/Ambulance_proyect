/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patient;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Objects;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author AdriCortellucci
 */
public class Users implements Serializable {

    private static final long serialVersionUID = -6291904286218553733L;
    private String user;
    byte[] encryptedPassword;
    private byte[] salt;

    public Users(String user) {
        this.user = user;
    }

    public Users(String user, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.user = user;
        setSalt();
        this.encryptedPassword = getEncryptedPassword(password, this.salt);
    }

    public String getUser() {
        return user;
    }

    public byte[] getsalt() {
        return salt;
    }

    public byte[] getPassword() {
        return encryptedPassword;
    }

    public void setSalt() throws NoSuchAlgorithmException {
        this.salt = generateSalt();
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(byte[] password) {
        this.encryptedPassword = password;
    }

    /* @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.user);
        hash = 47 * hash + Objects.hashCode(this.password);
        return hash;
    }*/
    public byte[] generateSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }

    public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }

    public byte[] getEncryptedPassword(String password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160;

        int iterations = 1000;

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);

        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

        return f.generateSecret(spec).getEncoded();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Users{" + "user=" + user + ", password=" + encryptedPassword + '}';
    }

}
