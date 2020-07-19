package Server.Controller;

import Server.Database;

import java.io.*;

public class ServerSaver {
    private static OutputStream outputStream;
    private static ObjectOutputStream objectOutputStream;
    public static void write(AllCommands command)  {
        try {
            writeDatabase();
        }
        catch (Exception e){
            System.out.println("there were no data folder recognized. but is it created now? : " + new File("src/main/resources/data").mkdir());
            write(command);
        }
    }

    private static void writeDatabase() throws IOException {
        outputStream = new FileOutputStream("src/main/resources/data/Database.dat");
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(Database.getInstance());
        objectOutputStream.close();
    }
}
