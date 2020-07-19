package Server.Controller;

import Server.Controller.UserSectionController.ManagerServerController;
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
        Database database = (Database) objectInputStream.readObject();
        Database.setInstance(database);
        try {
            ManagerServerController.getInstance().addCategory("file","file");
        }
        catch (Exception e) {
            if(!e.getMessage().equals("category exists with this name"))
                e.printStackTrace();
        }
        inputStream.close();
    }
}
