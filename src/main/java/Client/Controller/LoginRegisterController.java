package Client.Controller;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Server.Controller.LoginRegisterServerController;
import Server.Database;

public class LoginRegisterController {
    private static LoginRegisterController single_instance = null;
    public static LoginRegisterController getInstance()
    {
        if (single_instance == null)
            single_instance = new LoginRegisterController();

        return single_instance;
    }
    public void createAccount(Person person) throws Exception {
        LoginRegisterServerController.getInstance().createAccount(person);
    }
    public void login (String username,String password) throws Exception {
        Person person=LoginRegisterServerController.getInstance().login(username,password);
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
