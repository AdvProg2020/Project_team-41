package Server.Controller;

import Client.Models.Message.Message;
import Client.Models.Message.MessageTypes;
import Server.Controller.UserSectionController.ManagerServerController;

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
            } catch (Exception e) {
                try {
                    objectOutputStream.writeObject(new Message(e));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }
    }

    private void processMessage(Message message) throws Exception {
        MessageTypes.ClassTypes classTypes = message.getMessageType().getClassTypes();
        switch (classTypes) {
            case MANAGER_SECTION: {
                processManagerMessage(message);
                break;
            }
            case SELLER_SECTION:{
                processSellerMessage(message);
                break;
            }
            case BUYER_SECTION:{
                processBuyerMessage(message);
                break;
            }
        }
    }
    private void processManagerMessage(Message message) throws Exception {
        Object[] inputs = message.getInputs();
        switch (message.getMessageType()) {
            case ACCEPT_ALL_REQUESTS:{
                ManagerServerController.getInstance().acceptAllRequests();
                break;
            }
            case EDIT_CATEGORY_SPECIAL_FEATURES:{
                ManagerServerController.getInstance().editCategorySpecialFeatures((String)inputs[0],(String)inputs[1]);
                break;
            }
            case GET_ALL_PRODUCTS:{
                objectOutputStream.writeObject(ManagerServerController.getInstance().getAllProducts());
                break;
            }
            case GET_ALL_USERS:{
                objectOutputStream.writeObject(ManagerServerController.getInstance().getAllUsers());
                break;
            }
        }
    }
    private void processSellerMessage(Message message){

    }
    private void processBuyerMessage(Message message){

    }
}
