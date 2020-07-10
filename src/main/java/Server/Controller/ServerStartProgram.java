package Server.Controller;

import Server.Database;
import java.io.*;

public class ServerStartProgram {

    public static void startProgram() {
        try {
            readDatabase();
        }
        catch (Exception e){
            System.out.println("So it's the first time you run this program... Welcome!");
        }
    }

    private static void readDatabase() throws IOException, ClassNotFoundException {
        InputStream inputStream = new FileInputStream("src/main/resources/data/Database.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Database.setInstance(((Database) objectInputStream.readObject()));
        inputStream.close();
    }
}
