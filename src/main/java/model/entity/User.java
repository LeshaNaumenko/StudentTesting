package model.entity;

import utility.PasswordSecurity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

/**
 * Class {@code User} represents the User model. This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Alex Naumenko
 */
public class User implements Serializable {
    /**
     * Id of User.
     */
    private int id;

    /**
     * First name of User.
     */
    private String firstName;

    /**
     * Last name of User.
     */
    private String lastName;

    /**
     * Email of User.
     */
    private String email;

    /**
     * Hash of User.
     */
    private String hash;

    /**
     * Salt of User.
     */
    private byte[] salt;

    /**
     * Role of User.
     * @see model.entity.User.Role
     */
    private Role role;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructs user with user builder parameter.
     *
     * @param builder
     *
     * @see model.entity.User.Builder
     */
    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.hash = builder.hash;
        this.salt = builder.salt;
        this.role = builder.role;
    }

    /**
     * Returns id of User.
     *
     * @return id of User.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns first name of User.
     *
     * @return first name of User.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns Last name of User.
     *
     * @return Last name of User.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns email of User.
     *
     * @return email of User.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns hash of User.
     *
     * @return hash of User.
     */
    public String getHash() {
        return hash;
    }

    /**
     * Returns salt of User.
     *
     * @return salt of User.
     */
    public byte[] getSalt() {
        return salt;
    }

    public Role getRole() {
        return role;
    }


    /**
     * Indicates whether some other user is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same
     * as the o argument; {@code false} otherwise.
     * @see #hashCode()
     * @see java.util.HashMap
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (hash != null ? !hash.equals(user.hash) : user.hash != null) return false;
        if (!Arrays.equals(salt, user.salt)) return false;
        return role == user.role;
    }

    /**
     * Returns a hash code value for the user.
     *
     * @return a hash code value for this user.
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(salt);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    /**
     * Returns a string representation of the user.
     *
     * @return a string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hash='" + hash + '\'' +
                ", salt=" + Arrays.toString(salt) +
                ", role=" + role +
                '}';
    }

    /**
     * Class {@code Builder} is used to separate  the user's construction from its representation.
     */
    public static class Builder {

        /**
         * Id of user builder.
         */
        private int id;
        /**
         * FirstName of user builder.
         */
        private String firstName;
        /**
         * LastName of user builder.
         */
        private String lastName;
        /**
         * Email of user builder.
         */
        private String email;
        /**
         * Hash of user builder.
         */
        private String hash;
        /**
         * Salt of user builder.
         */
        private byte[] salt;
        /**
         * Role of user builder.
         */
        private Role role;

        /**
         * Set new id to user builder.
         *
         * @param id of user builder.
         * @return user builder.
         */
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        /**
         * Set new first name to user builder.
         *
         * @param firstName of user builder.
         * @return user builder.
         */

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Set new last name to user builder.
         *
         * @param lastName of user builder.
         * @return user builder.
         */

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Set new email to user builder.
         *
         * @param email of user builder.
         * @return user builder.
         */

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * This method determines the user's hash and salt according to the password provided.
         *
         * @param password provided by user.
         * @return user builder.
         *
         * @see utility.PasswordSecurity
         */

        public Builder setPassword(String password) {
            byte[] salt = new byte[0];
            try {
                salt = PasswordSecurity.getSalt();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }
            String hash = PasswordSecurity.getSecurePassword(password, salt);
            this.hash = hash;
            this.salt = salt;
            return this;
        }

        /**
         * Set new hash to user builder
         *
         * @param hash of user builder.
         * @return user builder
         */

        public Builder setHash(String hash) {
            this.hash = hash;
            return this;
        }

        /**
         * Set new salt to user builder
         *
         * @param salt of user builder.
         * @return user builder
         */

        public Builder setSalt(byte[] salt) {
            this.salt = salt;
            return this;
        }

        /**
         * Set new role to user builder
         *
         * @param role of user builder.
         * @return user builder
         */

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        /**
         * Returns the user builder.
         *
         * @return the user builder
         */
        public User build() {
            return new User(this);
        }
    }

    /**
     * Class {@code Role} defines roles such as administrator and user which specify what the user is allowed to do.
     */
    public enum Role {
        ADMIN, USER
    }

}
