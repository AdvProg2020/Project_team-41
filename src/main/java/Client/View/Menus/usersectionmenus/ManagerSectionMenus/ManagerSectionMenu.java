package Client.View.Menus.UserSectionMenus.ManagerSectionMenus;

import Client.View.Menus.LoginRegister.LoginForm;
import Client.View.Menus.Menu;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.App;
import org.example.Music;
import org.example.SimpleAudioPlayer;

import java.io.IOException;

import static Client.View.Menus.bank.BankWindow.openBank;

public class ManagerSectionMenu extends Menu {
    public BorderPane managerSectionBorderPane;
    public ScrollPane managerSectionButtonScrollPane;
    public static String parentFxmlAddress;

    @FXML
    public void initialize() {
        SimpleAudioPlayer.getInstance().playMusic(Music.USER_SECTION);
    }

    public void viewPersonalInfoButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/view personal info");
    }

    public void manageUsersButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/listUsersMenu/list users menu");
    }

    public void manageAllProductsButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/manageProductsMenu/manage products menu");
    }

    public void manageCategoriesButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/manageCategoriesMenu/manage categories menu");
    }

    public void manageRequestsButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/manageRequestsMenu/manage requests menu");
    }

    public void manageDiscountCodesButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/viewDiscountCodesMenu/view discount codes menu");
    }

    public void createDiscountCodeButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/viewDiscountCodesMenu/create discount code");
    }
    public void registerBackupPersonButtonClicked(MouseEvent mouseEvent) {
        setSubPage("backupPersonRegister");

    }

    public void financialSettingsClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/financialSettings/financial settings");
    }

    public void viewTradeLogs(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/viewTradeLogs/view tradeLogs menu");
    }

    private void setSubPage(String name){
        Parent root = null;
        try {
            root = App.loadFXML(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        managerSectionBorderPane.setCenter(root);
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
}
