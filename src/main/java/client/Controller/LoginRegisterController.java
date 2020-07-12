package Client.Controller;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.Person;
import Server.Controller.LoginRegisterServerController;

public class LoginRegisterController {
    private static LoginRegisterController single_instance = null;
    public static LoginRegisterController getInstance()
    {
        if (single_instance == null)
            single_instance = new LoginRegisterController();

        return single_instance;
    }
    public void createAccount(Person person) throws Exception {
//        LoginRegisterServerController.getInstance().createAccount(person);
        Connector.getInstance().initializeMessage(new Message(new Object[]{person}, MessageType.REGISTER));
    }
    public void login (String username,String password) throws Exception {
//        Person person=LoginRegisterServerController.getInstance().login(username,password);
        Person person= (Person)Connector.getInstance().initializeMessage(new Message(new Object[]{username, password}, MessageType.LOGIN));
        UserSectionController.setLoggedInPerson(person);
    }
    public void logout() throws Exception {
        UserSectionController.setLoggedInPerson(null);
        Connector.getInstance().initializeMessage(new Message(null, MessageType.LOGOUT));

    }
    public boolean checkIfManagerExists() throws Exception {
//        return LoginRegisterServerController.getInstance().checkIfManagerExists();
        return (boolean) Connector.getInstance().initializeMessage(new Message(null, MessageType.CHECK_MANAGER));

    }
    private LoginRegisterController(){
    }
}
