package Bank;

import Server.Controller.RandomNumberGenerator;

import java.io.RandomAccessFile;
import java.io.Serializable;

public class Account implements Serializable {
    private String  id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Account(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.id= RandomNumberGenerator.getIdNumber(5);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
