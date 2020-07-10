package Client.Models.Message;

import Client.Models.Person.Person;

public class LoginMessage extends Message{
    private String username;
    private String password;
    private Person person;

    public LoginMessage() {
        super();
    }

    public static Message loginMessage(String username, String password){
        LoginMessage message=new LoginMessage();
        message.username=username;
        message.password=password;
        message.messageType= MessageTypes.LOGIN;
        return message;
    }

    public static Message RegisterMessage(Person person){
        LoginMessage message=new LoginMessage();
        message.person=person;
        message.messageType= MessageTypes.REGISTER;
        return message;
    }
}
