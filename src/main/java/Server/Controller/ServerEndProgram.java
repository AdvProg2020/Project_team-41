package Server.Controller;

import Server.Database;

import java.io.*;

public class ServerEndProgram {
    private static OutputStream outputStream;
    private static ObjectOutputStream objectOutputStream;
    public static void endProgram()  {

        try{
            writeAllCategory();
            writeAllDiscountCodes();
            writeAllManagers();
            writeAllRequests();
            writeAllUsers();
            System.exit(0);
        }
        catch (Exception e){
            System.out.println("there were no resources folder recognized. but is it created now? : " + new File("src/main/resources").mkdir());
            endProgram();
        }
    }
    private static void writeAllCategory() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/allCategory.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getAllCategory());
         objectOutputStream.close();

    }
    private static void writeAllUsers() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/allUsers.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getAllUsers());
         objectOutputStream.close();

    }
    private static void writeAllRequests() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/allRequests.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getAllRequest());
         objectOutputStream.close();

    }
    private static void writeAllDiscountCodes() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/allDiscountCodes.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getAllDiscountCodes());
         objectOutputStream.close();

    }
    private static void writeAllManagers() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/allManagers.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getAllManagers());
         objectOutputStream.close();

    }
}
