package Client.Controller.UserSectionController;

import Client.Models.Person.Person;

import java.util.ArrayList;

public class UserSectionController {
    protected static Person loggedInPerson;
    public static ArrayList<String> viewPersonalInfo(Person person){
        if(person == null)
            throw new NullPointerException();
        //todo return ArrayList of personal info
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
