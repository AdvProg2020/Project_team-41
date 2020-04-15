package Client.Controller.UserSectionController;

import Client.Models.Person.Person;

import java.util.ArrayList;

public class UserSectionController {
    private static UserSectionController
            single_instance = null;
    public static UserSectionController
    getInstance()
    {
        if (single_instance == null)
            single_instance = new UserSectionController()
                    ;

        return single_instance;
    }
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
