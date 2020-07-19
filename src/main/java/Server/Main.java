package Server;
import Client.Models.Person.Person;
import Server.Controller.Backup;
import Server.Controller.ServerConnector;
import Server.Controller.ServerStartProgram;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static final int PORT = 8888;
    private static final ArrayList<Person> connectedPeople = new ArrayList<>();
    public static Backup backup;
    public static void main(String[] args) throws IOException {
        System.out.println("server started");
        ServerStartProgram.startProgram();
        System.out.println("database loaded");
        backup=new Backup();
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            System.out.println("waiting for client");
            Socket clientSocket = serverSocket.accept();
            new ServerConnector(serverSocket, clientSocket).start();
            System.out.println("client connected");
        }
    }

    public static ArrayList<Person> getConnectedPeople() {
        return connectedPeople;
    }
}