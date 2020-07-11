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
    public void logout(){
        UserSectionController.setLoggedInPerson(null);
        //todo other things
    }
    public boolean checkIfManagerExists(){
        return LoginRegisterServerController.getInstance().checkIfManagerExists();
    }
    private LoginRegisterController(){
    }
}
