package Client.Controller.UserSectionController;

import Client.Models.Person.Person;
import Server.Controller.UserSectionController.UserSectionServerController;

import java.util.ArrayList;

public abstract class UserSectionController {

    protected static Person loggedInPerson;
    public static ArrayList<String> getPersonalInfo(Person person){
        return UserSectionServerController.getPersonalInfo(person);
    }
    public static void edit(String field,String editedField) throws Exception {
        UserSectionServerController.edit(loggedInPerson,field,editedField);
    }

    public static Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public static void setLoggedInPerson(Person loggedInPerson) {
        UserSectionController.loggedInPerson = loggedInPerson;
    }
}