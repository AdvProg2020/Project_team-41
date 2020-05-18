package Server.Controller;

import Server.Database;

import java.io.*;

public class ServerSaver {
    private static OutputStream outputStream;
    private static ObjectOutputStream objectOutputStream;
    public static void write(AllCommands command)  {
        try {
        if(command.equals(AllCommands.allCategory)){
            writeAllCategory();
        }
        else if(command.equals(AllCommands.allDiscountCodes)){
            writeAllDiscountCodes();
        }
        else if(command.equals(AllCommands.allOffs)){
            writeAllOffs();
        }
        else if(command.equals(AllCommands.allRequests)){
            writeAllRequests();
        }
        else if(command.equals(AllCommands.allUsers)){
            writeAllUsers();
        }
        else if(command.equals(AllCommands.allData)){
            writeAllUsers();
            writeAllRequests();
            writeAllOffs();
            writeAllDiscountCodes();
            writeAllCategory();
        }
        else{
            System.err.println("invalid command in database");
        }
        }
        catch (Exception e){
            System.out.println("there were no resources folder recognized. but is it created now? : " + new File("src/main/resources").mkdir());
            write(command);
        }
    }
    private static void writeAllCategory() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/allCategory.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getAllCategory());
         objectOutputStream.close();

    }
    private static void writeAllOffs() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/allOffs.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getAllOffs());
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
}
