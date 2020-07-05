package Client.Models.Message;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Person;

public class Message {
    private String sender;

    private MessageType messageType;
    private String username;
    private String password;
    private Person person;


    private Message() {
        this.sender = UserSectionController.getLoggedInPerson().getUserName();
    }

    public static Message loginMessage(String username,String password){
        Message message=new Message();
        message.username=username;
        message.password=password;
        message.messageType=MessageType.LOGIN;
        return message;
    }

    public static Message RegisterMessage(Person person){
        Message message=new Message();
        message.person=person;
        message.messageType=MessageType.REGISTER;
        return message;
    }
}
