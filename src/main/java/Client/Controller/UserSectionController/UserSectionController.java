package Client.Controller.UserSectionController;

import Client.Models.Person.Person;

import java.util.ArrayList;

public abstract class UserSectionController {

    protected static Person loggedInPerson;
    public static ArrayList<String> getPersonalInfo(Person person){
        if(person == null)
            throw new NullPointerException();
        //todo return ArrayList of personal info
        ArrayList<String> personalInfo = new ArrayList<>();
        personalInfo.add("UserName: " + person.getUserName());
        personalInfo.add("FirstName: " + person.getFirstName());
        personalInfo.add("LastName: " + person.getLastName());
        personalInfo.add("Credit: " + person.getCredit());
        personalInfo.add("Email: " + person.getEmail());
        personalInfo.add("PhoneNumber: " + person.getPhoneNumber());
        //personalInfo.addAll(loggedInPerson.getDiscountCodes());
        //optional to add show discount code or show trade logs here
        //todo check if needed ot print discountCodes
        //todo check if it needs tradeLogs


        return personalInfo;

    }
    public static void edit(String field,String editedField) throws Exception {
        //todo edit fields except username
        if(field.equals("username"))
            throw new Exception("can't change username");
        if(loggedInPerson == null)
            throw new NullPointerException("There is no one logged in");
        switch (field){
            case "password":{
                loggedInPerson.setPassword(editedField);
            }
            case "firstName":{
                loggedInPerson.setFirstName(editedField);
            }
            case "lastName":{
                loggedInPerson.setLastName(editedField);
            }
            case "email":{
                loggedInPerson.setEmail(editedField);
            }
            case "phoneNumber":{
                loggedInPerson.setPhoneNumber(editedField);
            }
            default:{
                //todo throw exception
            }
        }

    }

    public static Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public static void setLoggedInPerson(Person loggedInPerson) {
        UserSectionController.loggedInPerson = loggedInPerson;
    }

}
