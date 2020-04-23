package Server.Controller;

import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Server.Database;

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
    public void createAccount(Person person) throws Exception {
        ArrayList<Person> allUsers = Database.getAllUsers();
        for (Person user : allUsers) {
            if (user.getUserName().equals(person.getUserName())) {
                throw new Exception("Invalid UserName!");
            }
        }

        if (person instanceof Manager) {
            if (Database.getAllManagers().size()>0) {
                throw new Exception("You can't be a manager!");
            }
            Database.addManager((Manager) person);
        }
        Database.addUser(person);
        //todo file coding
    }
    public Person login (String username,String password) throws Exception {
        ArrayList<Person> allUsers = Database.getAllUsers();

        for (Person user : allUsers) {
            if (user.getUserName().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                } else throw new Exception("Invalid Password");
            }
        }

        throw new Exception("Invalid UserName");
    }
    public void logout(){

    }
}
