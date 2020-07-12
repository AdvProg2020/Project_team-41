package Client.View.Menus.BuyerBackup;

import Client.Controller.LoginRegisterController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Chat.ChatComment;
import Client.View.Menus.NodeFinder;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.App;

import java.io.IOException;

public class bSendBox {
    public TextField message;
    public AnchorPane sendAnchorPane;

    public void sendMessage(ActionEvent actionEvent) throws IOException {
        BorderPane borderPane= (BorderPane) NodeFinder.getParentById(sendAnchorPane,"chatBorderPane");
        Label receiver= (Label) NodeFinder.getChildById(borderPane,"username");
        ChatComment chatComment=new ChatComment(UserSectionController.getLoggedInPerson().getUserName(),receiver.getText(),message.getText());
        bComment.chatComment=chatComment;
        VBox vBox= (VBox) NodeFinder.getChildById(borderPane,"chatVBox");
        vBox.getChildren().add(App.loadFXML("buyerBackUp/comment"));
    }
}
