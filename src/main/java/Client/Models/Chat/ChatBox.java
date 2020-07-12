package Client.Models.Chat;

import Client.Models.Person.BackupPerson;
import Client.Models.Person.Buyer;

import java.util.ArrayList;

public class ChatBox {
    private ArrayList<ChatComment> chatComments;
    private String backupPerson;
    private String  buyer;

    public ChatBox(String  backupPerson, String  buyer) {
        this.backupPerson = backupPerson;
        this.buyer = buyer;
        chatComments=new ArrayList<>();
    }

}
