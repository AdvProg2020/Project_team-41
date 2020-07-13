package Client.Controller.UserSectionController;

import Client.Controller.Connector;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.Person;
import Server.Controller.UserSectionController.UserSectionServerController;

import java.util.ArrayList;

public abstract class UserSectionController {

    protected static Person loggedInPerson;
    public static ArrayList<String> getPersonalInfo(Person person) throws Exception {
        return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(new Object[]{person}, MessageType.GET_PERSONAL_INFO));
        //return UserSectionServerController.getPersonalInfo(person);
    }
    public static void edit(String field,String editedField) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,field,editedField}, MessageType.EDIT));
//        UserSectionServerController.edit(loggedInPerson,field,editedField);
    }

    public static int getManagerAccountId() throws Exception {
        return (int) Connector.getInstance().initializeMessage(new Message(new Object[]{}, MessageType.GET_MANAGER_ACCOUNT_ID));
    }

    public static Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public static void setLoggedInPerson(Person loggedInPerson) {
        UserSectionController.loggedInPerson = loggedInPerson;
    }
    public static int getWage() throws Exception {
        return (int) Connector.getInstance().initializeMessage(new Message(null, MessageType.GET_WAGE));
    }
}