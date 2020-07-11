package Client.View.Menus.UserSectionMenus.BackupPersonMenu;

import Client.Models.Chat.ChatComment;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Comment {
    public static ChatComment chatComment;
    public TextField message;
    public Label sender;
    public void initialize(){
        message.setText(chatComment.getComment());
        sender.setText(chatComment.getSender());
    }
}
