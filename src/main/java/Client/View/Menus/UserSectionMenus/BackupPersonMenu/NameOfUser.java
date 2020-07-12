package Client.View.Menus.UserSectionMenus.BackupPersonMenu;

import Client.Controller.Connector;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Chat.ChatBox;
import Client.Models.Chat.ChatComment;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.View.Menus.BuyerBackup.bComment;
import Client.View.Menus.BuyerBackup.bNameBox;
import Client.View.Menus.NodeFinder;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.App;

import java.io.IOException;

public class NameOfUser {
    public static String user;
    public Label username;
    public AnchorPane userAnchorPane;

    public void initialize(){
        username.setText(user);
    }

    public void openChat(ActionEvent touchEvent) throws IOException {
        BorderPane borderPane = (BorderPane) NodeFinder.getParentById(userAnchorPane, "mainBorderPane");
        AnchorPane nameAnchorPane = (AnchorPane) NodeFinder.getChildById(borderPane, "nameAnchorPane");
        nameAnchorPane.getChildren().clear();
        NameBox.user = username.getText();
        nameAnchorPane.getChildren().add(App.loadFXML("userSection/backupPersonSection/nameBox"));

        AnchorPane sendAnchorPane = (AnchorPane) NodeFinder.getChildById(borderPane, "sendAnchorPane");
        sendAnchorPane.getChildren().clear();
        sendAnchorPane.getChildren().add(App.loadFXML("userSection/backupPersonSection/sendBox"));


        ChatBox chatBox = null;
        try {
            chatBox = (ChatBox) Connector.getInstance().initializeMessage(new Message(new Object[]{UserSectionController.getLoggedInPerson().getUserName(),
                    username.getText()}, MessageType.GET_CHAT_BOX));
        } catch (Exception e) {
            e.printStackTrace();
        }

        VBox vBox = (VBox) NodeFinder.getChildById(borderPane, "chatVBox");
        vBox.getChildren().clear();
        for (ChatComment chatComment : chatBox.getChatComments()) {
            Comment.chatComment = chatComment;
            vBox.getChildren().add(App.loadFXML("userSection/backupPersonSection/Comment"));

        }
    }
}
