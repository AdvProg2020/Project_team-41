package Models.Person;

import Models.TradeLogs;

import java.util.ArrayList;

public abstract class Person {
    private static ArrayList<Person> allUsers = new ArrayList<>();
    //start of personal information
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    //end of personal information

    private ArrayList<String>discountCodes;
    private int credit;
    private ArrayList<TradeLogs> tradeLogs;
}
