package Client.View.Menus.bank;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Controller.bankController.BankAPI;
import Client.Models.Person.Person;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import Client.View.Menus.UserSectionMenus.UserSection;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class BankLoginMenu {
    public TextField bankLoginMenuUsernameField;
    public PasswordField bankLoginMenuPasswordField;
    public Text bankLoginMenuInformationText;
    public AnchorPane bankLoginMenuAnchorPane;

    @FXML
    public void initialize(){
        try {
            BankAPI.makeInstance();
        } catch (IOException e) {
            e.printStackTrace();
            MessageTypeShow.showMessage(bankLoginMenuInformationText,MessageTypeShow.ERROR,"Error happened while connecting to bank");
        }
    }

    public void LoginClicked(MouseEvent mouseEvent) {
        try {
            BankAPI.getInstance().updateToken(bankLoginMenuUsernameField.getText(), bankLoginMenuPasswordField.getText());
            App.setRoot("bank/bankMainMenu");
        } catch (Exception e) {
            MessageTypeShow.showMessage(bankLoginMenuInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }

    public void createAccountClicked(MouseEvent mouseEvent) {
        BorderPane borderPane = (BorderPane)NodeFinder.getParentById(bankLoginMenuAnchorPane,"bankMainMenuBorderPane");
        BankMainMenu.setSubPage(borderPane,"bank/createBankAccountPage");
    }
}
