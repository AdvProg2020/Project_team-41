package Client.View.Menus.UserSectionMenus.SellerSectionMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Controller.bankController.BankAPI;
import Client.View.Menus.Menu;
import Client.View.Menus.bank.BankMainMenu;
import Client.View.Menus.bank.BankWindow;
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

import static Client.View.Menus.bank.BankWindow.openBank;

public class SellerSectionMenu extends Menu {
    public BorderPane sellerSectionBoarderPane;
    public ScrollPane sellerSectionButtonScrollPane;
    public TextField creditTextField;
    public AnchorPane insideAnchorPane;
    public AnchorPane outsideAnchorPane;
    public static String parentFxmlAddress;

    @FXML
    public void initialize(){
        SimpleAudioPlayer.getInstance().playMusic(Music.USER_SECTION);
       creditTextField.setText(SellerController.getLoggedInPerson().getCredit() +"Rials");
    }

    public void viewPersonalInfoButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/view personal info");
    }

    public void manageProducts(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/manageProductsMenu/manage products menu");
    }

    public void viewCompanyInfo(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/viewCompanyInfo/viewCompanyInfoMenu");
    }

    public void viewSalesHistory(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/viewTradeLogs/view tradeLogs menu");
    }

    public void showCategories(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/showCategoriesMenu/manage categories menu");
    }

    public void viewOffs(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/viewOffsMenu/manage offs menu");
    }

    public void addBid(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/AddBid");
    }

    public void decreaseCreditClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/decreaseCreditPage");

    }
    public void increaseCreditClicked(MouseEvent mouseEvent) {
        try {
            BankAPI.makeInstance();
            BankMainMenu.redirectedForTransfer = true;
            BankWindow.openBank();
        } catch (Exception e) {
            e.printStackTrace();
        }


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

    public void backCodeButtonClicked(MouseEvent mouseEvent) throws IOException {
        App.setRoot(parentFxmlAddress);
    }

    public void logoutButtonClicked(MouseEvent mouseEvent) throws IOException {
        logout("mainMenu");
    }

    public void bankSectionClicked(MouseEvent mouseEvent) throws IOException {
        openBank();
    }


    public void refreshCreditClicked(MouseEvent mouseEvent) {
        creditTextField.setText(UserSectionController.getLoggedInPerson().getCredit()+"Rials");
    }
}
