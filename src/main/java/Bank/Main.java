package Bank;

import Server.Controller.ServerConnector;
import Server.Controller.ServerStartProgram;
import Server.Database;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static final int PORT = 2222;

    public static void main(String[] args) throws IOException {
        System.out.println("Bank started");

        startProgram();

        System.out.println("database loaded");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            System.out.println("waiting for client");
            Socket clientSocket = serverSocket.accept();
            new BankClient(clientSocket).start();
            System.out.println("client connected");
        }
    }
    public static void startProgram()  {
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/bankData/BankDatabase.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            BankDatabase bankDatabase = (BankDatabase) objectInputStream.readObject();
            BankDatabase.setInstance(bankDatabase);
            inputStream.close();
        }catch (Exception e){
            System.out.println("So it's the first time you run Bank!");
        }
    }
    public static void writeDatabase(){
        try {
            OutputStream outputStream = new FileOutputStream("src/main/resources/bankData/BankDatabase.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(BankDatabase.getInstance());
            objectOutputStream.close();
        }catch (Exception e){
            System.out.println("there were no data folder recognized. but is it created now? : " +
                    new File("src/main/resources/bankData").mkdir());
            writeDatabase();

        }
    }
}
