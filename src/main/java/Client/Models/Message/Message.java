package Client.Models.Message;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Person;

public class Message {
    private String token;
    private String sender;
    private MessageType messageType;

    private Message() {
        this.sender = UserSectionController.getLoggedInPerson().getUserName();
    }
}
