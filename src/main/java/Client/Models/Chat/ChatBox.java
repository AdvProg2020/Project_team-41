package Client.Models.Chat;

import Client.Models.Person.BackupPerson;
import Client.Models.Person.Buyer;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatBox implements Serializable {
    private ArrayList<ChatComment> chatComments;
    private String backupPerson;
    private String  buyer;

    public ChatBox(String  backupPerson, String  buyer) {
        this.backupPerson = backupPerson;
        this.buyer = buyer;
        chatComments=new ArrayList<>();
    }

    public ArrayList<ChatComment> getChatComments() {
        return chatComments;
    }

    public String getBackupPerson() {
        return backupPerson;
    }

    public String getBuyer() {
        return buyer;
    }
    public void addComment(ChatComment chatComment){
        chatComments.add(chatComment);
    }
}
