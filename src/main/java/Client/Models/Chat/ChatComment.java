package Client.Models.Chat;

import Client.Models.Person.Person;

public class ChatComment {
    private String  sender;
    private String  receiver;
    private String comment;

    public ChatComment(String sender, String receiver, String comment) {
        this.sender = sender;
        this.receiver = receiver;
        this.comment = comment;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getComment() {
        return comment;
    }
}
