package Controller;

import Models.Category;
import Models.Person.Person;
import Models.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class Server {

    private static Server single_instance = null;
    public static Server getInstance()
    {
        if (single_instance == null)
            single_instance = new Server();

        return single_instance;
    }
    private Person loggedInPerson;
    private Category filterCategory;
    private HashMap<String ,String> filterFeature;
    private ArrayList<Product> cart;

    public Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public void setLoggedInPerson(Person loggedInPerson) {
        this.loggedInPerson = loggedInPerson;
    }


    public void setFilterCategory(Category filterCategory) {
        this.filterCategory = filterCategory;
    }

    public void setFilterFeature(HashMap<String, String> filterFeature) {
        this.filterFeature = filterFeature;
    }

    public Category getFilterCategory() {
        return filterCategory;
    }

    public HashMap<String, String> getFilterFeature() {
        return filterFeature;
    }
    public int create(String type,String user,String pass){
        //return -1 invalid User
        //return 1 correct
        //return 0 error type
    }
    public boolean login(String user,String pass){

    }
    public void logout(){

    }


}
