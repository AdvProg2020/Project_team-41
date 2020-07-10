package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageOffsMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.Models.Off;
import Client.View.Menus.MessageTypeShow;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;

public class ManageOffsMenu {
    public Text informationText;
    public AnchorPane manageOffsAnchorPane;
    public VBox offsVBox;
    public ScrollPane offsScrollPane;

    @FXML
    public void initialize(){
        ArrayList<Off> offs = null;
        try {
            offs = SellerController.getInstance().getOffs();
        } catch (Exception e) {
            showMessage(informationText,MessageTypeShow.ERROR,e.getMessage());
        }
        if (offs.isEmpty()) {
            showMessage(informationText, MessageTypeShow.INFORMATION, "No offs found");
        }
        for (Off off : offs) {
            makeOffSplitButton(off.getOffId());
        }
    }

    private void makeOffSplitButton(String offName) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/sellerSection/viewOffsMenu/offSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        VBox vBox = (VBox) anchorPane.getChildren().get(0);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        TextField textField = (TextField) gridPane.getChildren().get(0);
        textField.setText(offName);

        offsVBox.getChildren().add(root);
    }

    public void addOffClicked(MouseEvent mouseEvent) {
        AnchorPane insideAnchorPane = (AnchorPane)manageOffsAnchorPane.getParent();
        Parent root;
        try {
            root = App.loadFXML("userSection/sellerSection/viewOffsMenu/addOff");
            insideAnchorPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
}
