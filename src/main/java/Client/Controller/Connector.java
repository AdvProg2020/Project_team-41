package Client.Controller;

import Client.Models.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class Connector {
    private static Connector connector;
    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public static void makeInstance(Socket socket){
        Connector.connector = new Connector(socket);
    }

    public static Connector getInstance() {
        if (connector == null) {
            System.err.println("no socket found");
        }
        return connector;
    }
    private Connector(Socket socket){
        this.socket = socket;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendMessage(Message message) {
        try {
            System.out.println("message sent");
            if(!message.isLarge())
                System.out.println(message);
            System.err.println("----------------------");
            objectOutputStream.reset();
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object receiveMessage() throws Exception {
        Message message = null;
        try {
             message = (Message) objectInputStream.readObject();
            System.out.println("message received");
            if(!message.isLarge())
                System.out.println(message);
            System.err.println("----------------------");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert message != null;
        if(new Date().getTime()-message.getDate().getTime()>30*1000){
            sendMessage(new Message(new Exception("we got you sucker!")));
            return null;
        }
        message.checkForException();
        return message.getOutput();
    }

    public synchronized Object initializeMessage(Message message) throws Exception {
        Connector.getInstance().sendMessage(message);
        return Connector.getInstance().receiveMessage();
    }

}
