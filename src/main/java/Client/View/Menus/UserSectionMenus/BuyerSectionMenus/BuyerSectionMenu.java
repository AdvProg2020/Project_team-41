package Client.View.Menus.UserSectionMenus.BuyerSectionMenus;

import Client.Controller.UserSectionController.BuyerController;
import Client.View.Menus.Menu;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.example.App;
import org.example.Music;
import org.example.SimpleAudioPlayer;

import java.io.IOException;

public class BuyerSectionMenu extends Menu {
    public TextField creditTextField;
    public AnchorPane insideAnchorPane;
    public AnchorPane outsideAnchorPane;
    public BorderPane buyerSectionBoarderPane;
    public ScrollPane buyerSectionButtonScrollPane;
    public static String parentFxmlAddress;

    @FXML
    public void initialize(){
        SimpleAudioPlayer.getInstance().playMusic(Music.USER_SECTION);
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

    public void viewCartButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/buyerSection/viewCart/viewCart");
    }
    public static void loadInInsideAnchorPane(AnchorPane anchorPane,String location){
        Parent root = null;
        try {
            root = App.loadFXML(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
        anchorPane.getChildren().setAll(root);
    }

    public void logoutButtonClicked(MouseEvent mouseEvent) throws IOException {
        logout("mainMenu");
    }

    public void viewShoppingHistory(MouseEvent mouseEvent) {
        setSubPage("userSection/buyerSection/viewTradeLogs/view tradeLogs menu");
    }
}
