package Server.Controller;

import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Client.Models.Request;
import Server.Database;

import java.io.DataOutputStream;
import java.util.ArrayList;

public class LoginRegisterServerController {
    private ArrayList<Person> allUsersLoggedIn;
    private static LoginRegisterServerController single_instance = null;
    public static LoginRegisterServerController getInstance() {
        if (single_instance == null)
            single_instance = new LoginRegisterServerController();

        return single_instance;
    }
    private LoginRegisterServerController(){
    }
    public void createAccount(Person person) throws Exception {
        ArrayList<Person> allUsers = Database.getInstance().getAllUsers();
        for (Person user : allUsers) {
            if (user.getUserName().equals(person.getUserName())) {
                throw new Exception("Invalid UserName!");
            }
        }
        if(person instanceof Seller){
            Request request=new Request((Seller) person);
            Database.getInstance().addRequest(request);
        }else {
            Database.getInstance().addUser(person);
        }
    }
    public Person login (String username,String password) throws Exception {
        ArrayList<Person> allUsers = Database.getInstance().getAllUsers();

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
    public boolean checkIfManagerExists(){
        return !Database.getInstance().getAllManagers().isEmpty();
    }

}
