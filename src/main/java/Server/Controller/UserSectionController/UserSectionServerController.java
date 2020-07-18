package Server.Controller.UserSectionController;

import Client.Models.Person.Person;
import Server.Database;

import java.util.ArrayList;

public abstract class UserSectionServerController {
    public static ArrayList<String> getPersonalInfo(Person person) throws Exception {
        if(person == null)
            throw new NullPointerException();
        Person foundPerson = Database.getInstance().getPersonByUsername(person.getUserName());
        checkPersonPassword(person, foundPerson);
        person = foundPerson;
        ArrayList<String> personalInfo = new ArrayList<>();
        personalInfo.add("UserName: " + person.getUserName());
        personalInfo.add("FirstName: " + person.getFirstName());
        personalInfo.add("LastName: " + person.getLastName());
        personalInfo.add("Credit: " + person.getCredit());
        personalInfo.add("Email: " + person.getEmail());
        personalInfo.add("PhoneNumber: " + person.getPhoneNumber());


        return personalInfo;

    }
    public static int getManagerAccountId() {
        return Database.getInstance().getAccountId();
    }
    public static void edit(Person person,String field,String editedField) throws Exception {
        if(field.equals("username"))
            throw new Exception("can't change username");
        if(person == null)
            throw new NullPointerException("There is no one logged in");
        Person foundPerson = Database.getInstance().getPersonByUsername(person.getUserName());
        checkPersonPassword(person, foundPerson);
        person = foundPerson;
        switch (field.toLowerCase()){
            case "password":{
                String[] oldNewPasswords = editedField.split(",");
                if(oldNewPasswords.length < 2)
                    throw new Exception("enter passwords like i said");
                if(oldNewPasswords[0].equals(person.getPassword())) {
                    if(editedField.split(",").length != 2)
                        throw new Exception("enter like i said");
                    person.setPassword(editedField.split(",")[1]);
                }
                else
                    throw new Exception("wrong password");

                break;
            }
            case "firstname":{
                person.setFirstName(editedField);
                break;
            }
            case "lastname":{
                person.setLastName(editedField);
                break;
            }
            case "email":{
                person.setEmail(editedField);
                break;
            }
            case "phonenumber":{
                person.setPhoneNumber(editedField);
                break;
            }
            default:{
                throw new Exception("there is no field with this name!");
            }
        }

    }

    public static void increaseCredit(Person person,int credit) throws Exception {
        Person foundPerson = Database.getInstance().getPersonByUsername(person.getUserName());
        checkPersonPassword(person, foundPerson);
        foundPerson.increaseCredit(credit);
    }
    public static Person getLoggedInPerson(Person person) throws Exception {
        if (person == null) {
            return null;
        }
        Person foundPerson = Database.getInstance().getPersonByUsername(person.getUserName());
        checkPersonPassword(person, foundPerson);
        return foundPerson;
    }
    private static void checkPersonPassword(Person person,Person foundPerson) throws Exception {
        if (!foundPerson.getPassword().equals(person.getPassword())) {
            throw new Exception("you don't have access to this person");
        }
    }
}
