package Client.Models.Message;

import Client.Controller.UserSectionController.UserSectionController;

public class Message {
    private String token;
    private String sender;
    protected MessageTypes messageType;

    public Message() {
        this.sender = UserSectionController.getLoggedInPerson().getUserName();
    }

    public String getToken() {
        return token;
    }

    public String getSender() {
        return sender;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}