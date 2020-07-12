package Server.Controller;

import Client.Models.Chat.ChatBox;
import Client.Models.Chat.ChatComment;
import Client.Models.Person.BackupPerson;
import Client.Models.Person.Person;
import Server.Database;
import Server.Main;

import java.util.ArrayList;

public class Backup {
    ChatBox chatBox;
    public ArrayList<BackupPerson> getBackupPeople(){
        ArrayList<Person> people= Main.connectedPeople;
        ArrayList<BackupPerson> backupPeople=new ArrayList<>();
        if(!people.isEmpty()){
            for (Person person1 : people) {
                if(person1 instanceof BackupPerson){
                    backupPeople.add((BackupPerson) person1);
                }
            }
        }
        return backupPeople;
    }
    public ChatBox getChatBox(String backupPerson,String buyer){
        for (ChatBox chatBox : Database.getInstance().getChatBoxes()) {
            if(chatBox.getBackupPerson().equals(backupPerson)&&chatBox.getBuyer().equals(buyer)){
                this.chatBox=chatBox;
                return chatBox;
            }
        }
        chatBox=new ChatBox(backupPerson,buyer);
        Database.getInstance().addChatBox(chatBox);
        return chatBox;
    }
    public void addComment(ChatComment chatComment){
        chatBox.addComment(chatComment);
    }
}
