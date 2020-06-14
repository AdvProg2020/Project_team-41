package Client.View.Menus.UserSectionMenus.BuyerSectionMenus;

import Client.Controller.UserSectionController.BuyerController;
import Client.Controller.UserSectionController.SellerController;
import Client.Controller.UserSectionController.UserSectionController;
import Server.Database;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.example.App;

import java.io.IOException;

public class BuyerSectionMenu {
    public TextField creditTextField;
    public AnchorPane insideAnchorPane;
    public AnchorPane outsideAnchorPane;
    public BorderPane buyerSectionBoarderPane;
    public ScrollPane buyerSectionButtonScrollPane;
    public static String parentFxmlAddress;

    @FXML
    public void initialize(){
        //todo remove nextLine
        //UserSectionController.setLoggedInPerson(Database.getAllUsers().get(2));
        creditTextField.setText(BuyerController.getInstance().getBalance() +"Rials");
    }

    public void viewPersonalInfoButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/view personal info");
    }

    private void setSubPage(String name){
        Parent root = null;
        try {
            root = App.loadFXML(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        insideAnchorPane.getChildren().setAll(root);
    }

    public void viewDiscountCodesButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/buyerSection/showDiscountCodesMenu/view discount codes menu");
    }

    public void backCodeButtonClicked(MouseEvent mouseEvent) throws IOException {
        App.setRoot(parentFxmlAddress);
    }
}
