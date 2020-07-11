package Client.Models.Chat;

import Client.Models.Person.Person;

public class ChatComment {
    private String  seder;
    private String  receiver;
    private String comment;

    public ChatComment(String seder, String receiver, String comment) {
        this.seder = seder;
        this.receiver = receiver;
        this.comment = comment;
    }
}
