package Server.Controller;

import Client.Models.Category;
import Client.Models.CodedDiscount;
import Client.Models.Off;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Request;
import Server.Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ServerStartProgram {
    private static InputStream inputStream;
    private static ObjectInputStream objectInputStream;

    public static void startProgram() {
        try {
            readAllCategory();
            readAllOffs();
            readAllDiscountCodes();
            readAllUsers();
            readAllRequest();
        }
        catch (Exception e){
            System.out.println("So it's the first time you run this program... Welcome!");
        }
    }

    private static void readAllUsers() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/allUsers.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.setAllUsers((ArrayList<Person>) objectInputStream.readObject());
        inputStream.close();

    }
    private static void readAllOffs() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/allOffs.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.setAllOffs((ArrayList<Off>) objectInputStream.readObject());
        inputStream.close();

    }
    private static void readAllCategory() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/allCategory.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.setAllCategory((ArrayList<Category>) objectInputStream.readObject());
        inputStream.close();

    }
    private static void readAllDiscountCodes() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/allDiscountCodes.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.setAllDiscountCodes((ArrayList<CodedDiscount>) objectInputStream.readObject());
        inputStream.close();

    }
    private static void readAllRequest() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/allRequests.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.setAllRequest((ArrayList<Request>) objectInputStream.readObject());
        inputStream.close();

    }
}
