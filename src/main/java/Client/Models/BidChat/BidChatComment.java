package Client.Models.BidChat;

public class BidChatComment {
    String sender;
    String message;

    public BidChatComment(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
