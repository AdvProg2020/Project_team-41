package Client.Models.Person;

import Client.Models.Product;
import Client.Models.TradeLog;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Person implements Serializable {

    //start of personal information
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    //end of personal information

    private ArrayList<TradeLog> tradeLogs = new ArrayList<>();
    private ArrayList<String>discountCodes = new ArrayList<>();
    private int credit;

    public ArrayList<Product> getAllProductsHeTraded(){
        ArrayList<Product> AllProductsHeTraded = new ArrayList<>();
        for (TradeLog tradeLog : tradeLogs) {
            AllProductsHeTraded.addAll(tradeLog.getItems());
        }
        return AllProductsHeTraded;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<String> getDiscountCodes() {
        return discountCodes;
    }

    public int getCredit() {
        return credit;
    }

    public ArrayList<TradeLog> getTradeLogs() {
        return tradeLogs;
    }


    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", credit=" + credit +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getPassword() {
        return password;
    }
}
