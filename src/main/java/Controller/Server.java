package Controller;

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
    ArrayList<Person> allUsers;
    private Person loggedInPerson;

    public Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public void setLoggedInPerson(Person loggedInPerson) {
        this.loggedInPerson = loggedInPerson;
    }
}
