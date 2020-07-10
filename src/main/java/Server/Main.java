package Server;
import Server.Controller.ServerConnector;
import Server.Controller.ServerStartProgram;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static final int PORT = 8888;
    private static final ArrayList<Socket> clients = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        System.out.println("server started");
        ServerStartProgram.startProgram();
        System.out.println("database loaded");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            System.out.println("waiting for client");
            Socket clientSocket = serverSocket.accept();
            clients.add(clientSocket);
            new ServerConnector(serverSocket, clientSocket).run();
            System.out.println("client connected");
        }
    }


}