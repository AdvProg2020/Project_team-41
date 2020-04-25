package Server;

import Client.Models.Category;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Request;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Category> allCategory=new ArrayList<>();
    private static ArrayList<Request> allRequest=new ArrayList<>();
    private static ArrayList<Person> allUsers=new ArrayList<>();
    private static ArrayList<Manager>allManagers=new ArrayList<>();
    //todo move all here
    

    public static ArrayList<Person> getAllUsers(){
        return allUsers;
    }

    public static ArrayList<Manager> getAllManagers() {
        return allManagers;
    }
    public static void addManager(Manager manager){
        allManagers.add(manager);
    }
    public static void addUser(Person person){
        allUsers.add(person);
    }

}
