package Server.Controller;

import Client.Models.Chat.ChatBox;
import Client.Models.Chat.ChatComment;
import Client.Models.Person.BackupPerson;
import Client.Models.Person.Person;
import Server.Database;
import Server.Main;

import java.util.ArrayList;

public class Backup {
    public synchronized ArrayList<BackupPerson> getBackupPeople(){
        ArrayList<Person> people= Main.getConnectedPeople();
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
    public synchronized ChatBox getChatBox(String backupPerson,String buyer){
//        ServerStartProgram.startProgram();
        //I know it's not right
        for (ChatBox chatBox : Database.getInstance().getChatBoxes()) {
            if(chatBox.getBackupPerson().equals(backupPerson)&&chatBox.getBuyer().equals(buyer)){
                return chatBox;
            }
        }
        ChatBox chatBox=new ChatBox(backupPerson,buyer);
        Database.getInstance().addChatBox(chatBox);
        return chatBox;
    }
    public synchronized void addComment(ChatComment chatComment){
        for (ChatBox chatBox : Database.getInstance().getChatBoxes()) {
            if((chatComment.getSender().equals(chatBox.getBackupPerson())&&chatComment.getReceiver().equals(chatBox.getBuyer()))
                    ||(chatComment.getSender().equals(chatBox.getBuyer())&&chatComment.getReceiver().equals(chatBox.getBackupPerson()))){
                chatBox.addComment(chatComment);
            }
        }
    }
    public synchronized ArrayList<ChatBox> getAllChatBoxes(String backupPerson){
        ArrayList<ChatBox> chatBoxes=new ArrayList<>();
        for (ChatBox box : Database.getInstance().getChatBoxes()) {
            if(box.getBackupPerson().equals(backupPerson)){
                chatBoxes.add(box);
            }
        }
        return chatBoxes;
    }
}
