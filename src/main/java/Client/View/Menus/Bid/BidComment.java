package Client.View.Menus.Bid;

import Client.Models.BidChat.BidChatComment;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BidComment {
    public static BidChatComment bidChatComment;
    public TextField message;
    public Label sender;
    public void initialize(){
        sender.setText(bidChatComment.getSender());
        message.setText(bidChatComment.getMessage());
    }
}
