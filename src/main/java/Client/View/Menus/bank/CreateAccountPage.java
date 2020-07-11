package Client.View.Menus.bank;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Controller.bankController.BankAPI;
import Client.Models.Person.Person;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.example.App;

public class CreateAccountPage {
    public TextField createAccountMenuUsernameField;
    public PasswordField createAccountMenuPasswordField;
    public Text createAccountMenuInformationText;
    public PasswordField createAccountMenuRepeatPasswordField;

    public void createAccountClicked(MouseEvent mouseEvent) {
        Person loggedInPerson = UserSectionController.getLoggedInPerson();
        try {
            int id = BankAPI.getInstance().createAccount(loggedInPerson.getFirstName(), loggedInPerson.getLastName(), createAccountMenuUsernameField.getText(), createAccountMenuPasswordField.getText(), createAccountMenuRepeatPasswordField.getText());
            AnchorPane bankLoginMenuAnchorPane = (AnchorPane)NodeFinder.getParentById(createAccountMenuInformationText,"bankLoginMenuAnchorPane");
            bankLoginMenuAnchorPane.getChildren().setAll( App.loadFXML("bank/bankLoginMenu"));
            Text text = (Text) NodeFinder.getChildById(bankLoginMenuAnchorPane, "bankLoginMenuInformationText");
            MessageTypeShow.showMessage(text, MessageTypeShow.SUCCESS, "Successfully created account. your account id is: " + id);
        } catch (Exception e) {
            MessageTypeShow.showMessage(createAccountMenuInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }
}
