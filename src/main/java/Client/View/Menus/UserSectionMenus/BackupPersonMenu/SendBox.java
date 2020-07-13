package Client.View.Menus.UserSectionMenus.BackupPersonMenu;

import Client.Controller.Connector;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Chat.ChatComment;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.View.Menus.BuyerBackup.bComment;
import Client.View.Menus.NodeFinder;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.App;

import java.io.IOException;

public class SendBox {
    public TextField message;
    public AnchorPane sendAnchorPane;

    public void sendMessage(ActionEvent actionEvent) throws IOException {
        BorderPane borderPane= (BorderPane) NodeFinder.getParentById(sendAnchorPane,"chatBorderPane");
        Label receiver= (Label) NodeFinder.getChildById(borderPane,"username");
        ChatComment chatComment=new ChatComment(UserSectionController.getLoggedInPerson().getUserName(),receiver.getText(),message.getText());
        Comment.chatComment=chatComment;
        VBox vBox= (VBox) NodeFinder.getChildById(borderPane,"chatVBox");
        vBox.getChildren().add(App.loadFXML("userSection/backupPersonSection/Comment"));
        try {
            Connector.getInstance().initializeMessage(new Message(new Object[]{chatComment}, MessageType.SEND_COMMENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        message.clear();
    }
}
