package Server.Controller;

import Client.Models.Person.Person;

import java.util.ArrayList;

public class UserSectionController {
    private static Person loggedInPerson;
    public static ArrayList<String> viewPersonalInfo(Person person){
        if(person == null)
            throw new NullPointerException();
        //todo return ArrayList of personal info
    }

    public static Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public static void setLoggedInPerson(Person loggedInPerson) {
        UserSectionController.loggedInPerson = loggedInPerson;
    }
    public static void edit(String field){
        //todo edit fields except username
    }

}
