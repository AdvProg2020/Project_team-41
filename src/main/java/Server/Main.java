package Server;
import Server.Controller.ServerConnector;
import Server.Controller.ServerStartProgram;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("server started");
        ServerStartProgram.startProgram();
        System.out.println("database loaded");
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            System.out.println("waiting for client");
            new ServerConnector(serverSocket, serverSocket.accept()).run();
            System.out.println("client connected");
        }
    }


}