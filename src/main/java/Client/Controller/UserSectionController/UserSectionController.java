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
        personalInfo.add(loggedInPerson.getUserName());
        personalInfo.add(loggedInPerson.getFirstName());
        personalInfo.add(loggedInPerson.getLastName());
        personalInfo.add(Integer.toString(loggedInPerson.getCredit()));
        personalInfo.add(loggedInPerson.getEmail());
        personalInfo.add(loggedInPerson.getPhoneNumber());
        personalInfo.addAll(loggedInPerson.getDiscountCodes());
        //todo check if it needs tradeLogs


        return personalInfo;

    }
    public static void edit(String field){
        //todo edit fields except username
    }

    public static Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public static void setLoggedInPerson(Person loggedInPerson) {
        UserSectionController.loggedInPerson = loggedInPerson;
    }

}
