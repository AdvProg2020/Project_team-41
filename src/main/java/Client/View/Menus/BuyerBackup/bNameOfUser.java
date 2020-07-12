package Client.View.Menus.BuyerBackup;

import Client.View.Menus.NodeFinder;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.example.App;

import java.io.IOException;

public class bNameOfUser {
    public static String user;
    public Label username;
    public AnchorPane userAnchorPane;

    public void initialize(){
        username.setText(user);
    }

    public void openChat(ActionEvent touchEvent) throws IOException {
        BorderPane borderPane= (BorderPane) NodeFinder.getParentById(userAnchorPane,"mainBorderPane");
        AnchorPane nameAnchorPane= (AnchorPane) NodeFinder.getChildById(borderPane,"nameAnchorPane");
        bNameBox.user=username.getText();
        nameAnchorPane.getChildren().add(App.loadFXML("buyerBackUp/nameBox"));

        AnchorPane sendAnchorPane= (AnchorPane) NodeFinder.getChildById(borderPane,"sendAnchorPane");
        sendAnchorPane.getChildren().add(App.loadFXML("buyerBackUp/sendBox"));


    }
}
