package Server.Controller;

import Server.Database;

import java.io.*;

public class ServerSaver {
    private static OutputStream outputStream;
    private static ObjectOutputStream objectOutputStream;
    public static void write(AllCommands command)  {

        try {
            writeDatabase();
//        if(command.equals(AllCommands.allCategory)){
//            writeAllCategory();
//        }
//        else if(command.equals(AllCommands.allDiscountCodes)){
//            writeAllDiscountCodes();
//        }
//        else if(command.equals(AllCommands.allOffs)){
//            writeAllOffs();
//        }
//        else if(command.equals(AllCommands.allRequests)){
//            writeAllRequests();
//        }
//        else if(command.equals(AllCommands.allUsers)){
//            writeAllUsers();
//        }
//        else if(command.equals(AllCommands.allData)){
//            writeAllUsers();
//            writeAllRequests();
//            writeAllOffs();
//            writeAllDiscountCodes();
//            writeAllCategory();
//        }
//        else{
//            System.err.println("invalid command in database");
//        }
        }
        catch (Exception e){
            System.out.println("there were no data folder recognized. but is it created now? : " + new File("src/main/resources/data").mkdir());
            write(command);
        }
    }
    private static void writeAllCategory() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/data/allCategory.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getInstance().getAllCategory());
         objectOutputStream.close();

    }
    private static void writeAllOffs() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/data/allOffs.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getInstance().getAllOffs());
         objectOutputStream.close();

    }
    private static void writeAllUsers() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/data/allUsers.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getInstance().getAllUsers());
         objectOutputStream.close();

    }
    private static void writeAllRequests() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/data/allRequests.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getInstance().getAllRequest());
         objectOutputStream.close();

    }
    private static void writeAllDiscountCodes() throws IOException {
         outputStream = new FileOutputStream("src/main/resources/data/allDiscountCodes.dat");
         objectOutputStream = new ObjectOutputStream(outputStream);
         objectOutputStream.writeObject(Database.getInstance().getAllDiscountCodes());
         objectOutputStream.close();

    }
    private static void writeDatabase() throws IOException {
        outputStream = new FileOutputStream("src/main/resources/data/Database.dat");
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(Database.getInstance());
        objectOutputStream.close();
    }
}
