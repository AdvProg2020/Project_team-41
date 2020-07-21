package Server.Controller;

import Server.Controller.UserSectionController.ManagerServerController;
import Server.Database;
import java.io.*;

public class ServerStartProgram {

    public static void startProgram() {
        try {
            readDatabase();
        }
        catch (Exception e) {
            System.out.println("So it's the first time you run this program... Welcome!");
            Database.getInstance();
            try {
                ManagerServerController.getInstance().addCategory("file", "file");
            } catch (Exception er) {
                if (!er.getMessage().equals("category exists with this name"))
                    er.printStackTrace();
            }

        }
    }

    private static void readDatabase() throws IOException, ClassNotFoundException {
        InputStream inputStream = new FileInputStream("src/main/resources/data/Database.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Database database = (Database) objectInputStream.readObject();
        Database.setInstance(database);
        inputStream.close();
    }
}
