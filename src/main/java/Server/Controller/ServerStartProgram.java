package Server.Controller;

import Client.Models.Category;
import Client.Models.CodedDiscount;
import Client.Models.Off;
import Client.Models.Person.Person;
import Client.Models.Request;
import Server.Database;

import java.io.*;
import java.util.ArrayList;

public class ServerStartProgram {
    private static InputStream inputStream;
    private static ObjectInputStream objectInputStream;

    public static void startProgram() {
        try {
            readDatabase();
        }
        catch (Exception e){
            System.out.println("So it's the first time you run this program... Welcome!");
        }
    }

    private static void readAllUsers() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/data/allUsers.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.getInstance().setAllUsers((ArrayList<Person>) objectInputStream.readObject());
        inputStream.close();

    }
    private static void readAllOffs() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/data/allOffs.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.getInstance().setAllOffs((ArrayList<Off>) objectInputStream.readObject());
        inputStream.close();

    }
    private static void readAllCategory() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/data/allCategory.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.getInstance().setAllCategory((ArrayList<Category>) objectInputStream.readObject());
        inputStream.close();

    }
    private static void readAllDiscountCodes() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/data/allDiscountCodes.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.getInstance().setAllDiscountCodes((ArrayList<CodedDiscount>) objectInputStream.readObject());
        inputStream.close();

    }
    private static void readAllRequest() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/data/allRequests.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.getInstance().setAllRequest((ArrayList<Request>) objectInputStream.readObject());
        inputStream.close();

    }
    private static void readDatabase() throws IOException, ClassNotFoundException {
        inputStream = new FileInputStream("src/main/resources/data/Database.dat");
        objectInputStream = new ObjectInputStream(inputStream);
        Database.setInstance(((Database) objectInputStream.readObject()));
        inputStream.close();
    }
}
