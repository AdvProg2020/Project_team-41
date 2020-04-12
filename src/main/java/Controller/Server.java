package Controller;

import Models.Category;
import Models.Person.Person;

import java.util.ArrayList;

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

    public Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public void setLoggedInPerson(Person loggedInPerson) {
        this.loggedInPerson = loggedInPerson;
    }
}
