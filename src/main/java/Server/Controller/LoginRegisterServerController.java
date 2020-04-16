package Server.Controller;

import Client.Models.Person.Person;

import java.util.ArrayList;

public class LoginRegisterServerController {
    private ArrayList<Person> allUsersLoggedIn;
    private static LoginRegisterServerController single_instance = null;
    public static LoginRegisterServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new LoginRegisterServerController();

        return single_instance;
    }
    private LoginRegisterServerController(){
    }
    public void createAccount(String type,String username,String Password){

    }
    public void login (String username,String password){

    }
    public void logout(){

    }
}
