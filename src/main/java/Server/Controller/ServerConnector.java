package Server.Controller;


import Client.Models.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector implements Runnable {
    private static ServerSocket serverSocket;
    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    public ServerConnector(ServerSocket serverSocket,Socket socket){
        if(ServerConnector.serverSocket == null) {
            ServerConnector.serverSocket = serverSocket;
        }
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Message message = (Message) objectInputStream.readObject();
                processMessage(message);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void processMessage(Message message) {
        switch (message.getMessageType()) {

        }
    }
}
