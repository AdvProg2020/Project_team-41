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
    private ArrayList<Person> allUsers;
    private Person loggedInPerson;
    private ArrayList<Category>categories;
    private Category filterCategory;
    private HashMap<String ,String> filterFeature;

    public Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public void setLoggedInPerson(Person loggedInPerson) {
        this.loggedInPerson = loggedInPerson;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
    public

}
