package Client.Controller;

import Client.Models.Message.Message;

import java.io.IOException;
import java.net.Socket;

public class Connector {
    //singleton
    String token;
    Socket socket;
    private Connector(){
        try {
            socket = new Socket("localhost", 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        //send message to server

    }

    public Object receiveMessage(){
        //read
        //
        return null;
    }

}
