package Client.Models.Person;

import Client.Models.Product;
import Client.Models.TradeLog;
import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Person implements Serializable {

    //start of personal information
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private static int minimumCredit;
    //end of personal information

    private final ArrayList<TradeLog> tradeLogs = new ArrayList<>();
    private int credit;

    public static void setMinimumCredit(int minimumCredit) {
        Person.minimumCredit = minimumCredit;
    }
    public ArrayList<Product> getAllProductsHeTraded(){
        ArrayList<Product> AllProductsHeTraded = new ArrayList<>();
        for (TradeLog tradeLog : tradeLogs) {
            AllProductsHeTraded.addAll(tradeLog.getItems().keySet());
        }
        return AllProductsHeTraded;
    }

    public void setUserName(String userName) throws Exception {
        if(userName.isBlank())
            throw new Exception("username can't be blank!");
        this.userName = userName;
        ServerSaver.write(AllCommands.allData);
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

    public int getCredit() {
        return credit;
    }

    public ArrayList<TradeLog> getTradeLogs() {
        return tradeLogs;
    }

    public void addTradeLog(TradeLog tradeLog){
        tradeLogs.add(tradeLog);
        ServerSaver.write(AllCommands.allData);
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

    public void setFirstName(String firstName) throws Exception {
        if(firstName.isBlank())
            throw new Exception("first name can't be blank!");
        this.firstName = firstName;
        ServerSaver.write(AllCommands.allData);
    }

    public void setLastName(String lastName) throws Exception {
        if(lastName.isBlank())
            throw new Exception("last name can't be blank!");
        this.lastName = lastName;
        ServerSaver.write(AllCommands.allData);
    }

    public void setEmail(String email) throws Exception {
        if(!email.matches("\\S+@\\S+\\.\\S+"))
            throw new Exception("please enter a valid email address");
        this.email = email;
        ServerSaver.write(AllCommands.allData);
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {
        if(!phoneNumber.matches("\\d+"))
            throw new Exception("please enter a valid phone number");
        this.phoneNumber = phoneNumber;
        ServerSaver.write(AllCommands.allData);
    }

    public void setPassword(String password) throws Exception {
        if(password.isBlank())
            throw new Exception("password can't be blank!");
        this.password = password;
        ServerSaver.write(AllCommands.allData);
    }

    public void increaseCredit(int credit) throws Exception {
        if(credit<0)
            throw new Exception("credit can't be negative!");
        this.credit += credit;
        ServerSaver.write(AllCommands.allData);
    }
    public void decreaseCredit(int credit) throws Exception {
        if(credit<getCredit()-getMinimumCredit()) {
            setCredit(getCredit() - credit);
            ServerSaver.write(AllCommands.allData);
        }
        else
            throw new Exception("there isn't enough credit");
    }

    public void setCredit(int credit) throws Exception {
        if(credit<0)
            throw new Exception("credit can't be negative!");
        this.credit = credit;
        ServerSaver.write(AllCommands.allData);
    }
    public String getPassword() {
        return password;
    }

    public static int getMinimumCredit() {
        return minimumCredit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return credit == person.credit &&
                userName.equals(person.userName) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(email, person.email) &&
                Objects.equals(phoneNumber, person.phoneNumber) &&
                password.equals(person.password) &&
                Objects.equals(tradeLogs, person.tradeLogs);
    }

}
