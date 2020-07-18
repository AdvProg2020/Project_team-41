package Client.Models.BidChat;

public class BidChatComment {
    public String bidId;
    String sender;
    String message;

    public BidChatComment(String sender, String message,String bidId) {
        this.sender = sender;
        this.message = message;
        this.bidId=bidId;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getBidId() {
        return bidId;
    }
}
