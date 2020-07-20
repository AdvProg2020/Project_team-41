package Bank;

import Server.Controller.RandomNumberGenerator;

import java.io.Serializable;

public class Account implements Serializable {
    private int  id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int credit;


    public Account(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.id= Integer.parseInt(RandomNumberGenerator.getIdNumber(5));
        this.credit=0;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public int getCredit() {
        return credit;
    }
    public void addCredit(int money){
        this.credit+=money;
    }
    public void removeCredit(int money){
        this.credit-=money;
    }
}
