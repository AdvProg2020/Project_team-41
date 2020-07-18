package Client.Models.BidChat;

import Client.Models.Bid;
import Client.View.Menus.Bid.BidComment;
import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.util.ArrayList;

public class BidChatBox implements Serializable {
    ArrayList<BidChatComment>chatComments;
    String bidId;

    public BidChatBox(String bidId) {
        this.bidId = bidId;
        this.chatComments=new ArrayList<>();
    }

    public ArrayList<BidChatComment> getChatComments() {
        return chatComments;
    }

    public String getBidId() {
        return bidId;
    }
    public void addComment(BidChatComment bidChatComment){
        chatComments.add(bidChatComment);
        ServerSaver.write(AllCommands.allCategory);
    }
}
