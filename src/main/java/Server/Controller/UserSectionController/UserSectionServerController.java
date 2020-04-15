package Server.Controller.UserSectionController;

import Client.Models.Person.Person;

import java.util.ArrayList;

public abstract class UserSectionServerController {

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
        UserSectionServerController.loggedInPerson = loggedInPerson;
    }

}
