package Client.Models.Chat;

import Client.Models.Person.BackupPerson;
import Client.Models.Person.Buyer;

import java.util.ArrayList;

public class ChatBox {
    private ArrayList<ChatComment> chatComments;
    private BackupPerson backupPerson;
    private Buyer buyer;

    public ChatBox(BackupPerson backupPerson, Buyer buyer) {
        this.backupPerson = backupPerson;
        this.buyer = buyer;
        chatComments=new ArrayList<>();
    }

}
