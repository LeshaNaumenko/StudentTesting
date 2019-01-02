package model.entity;

import connection.MysqlConnection;
import utility.PasswordSecurity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.*;

public class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private byte[] salt;
    private Role role;


    public User() {
    }

    public User(int id, String firstName, String lastName, String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.salt = builder.salt;
        this.role = builder.role;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public byte[] getSalt(){
        return salt;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public enum Role {
        ADMIN, USER
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private byte[] salt;
        private Role role;

        public Builder setId(int Id) {
            this.id = Id;
            return this;
        }

        public Builder setFirstName(String FirstName) {
            this.firstName = FirstName;
            return this;
        }

        public Builder setLastName(String LastName) {
            this.lastName = LastName;
            return this;
        }

        public Builder setEmail(String Email) {
            this.email = Email;
            return this;
        }

        public Builder setPassword(String Password) {
            byte[] salt = new byte[0];
            try {
                salt = PasswordSecurity.getSalt();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }
            String hash = PasswordSecurity.getSecurePassword(Password, salt);
            this.password = hash;
            this.salt = salt;
            return this;
        }

        public Builder setHash(String hash) {
            this.password = hash;
            return this;
        }

        public Builder setSalt(byte[] salt) {
            this.salt = salt;
            return this;
        }

        public Builder setRole(Role Role) {
            this.role = Role;
            return this;
        }


        public User build() {
            return new User(this);
        }
    }
}
