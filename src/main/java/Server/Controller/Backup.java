package Server.Controller;

import Client.Models.Person.BackupPerson;
import Client.Models.Person.Person;
import Server.Main;

import java.util.ArrayList;

public class Backup {
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
}
