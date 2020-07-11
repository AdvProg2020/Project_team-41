package Client.View.Menus.bank;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Controller.bankController.BankAPI;
import Client.Models.Person.Person;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CreateAccountPage {
    public TextField createAccountMenuUsernameField;
    public PasswordField createAccountMenuPasswordField;
    public Text createAccountMenuInformationText;
    public PasswordField createAccountMenuRepeatPasswordField;

    public void createAccountClicked(MouseEvent mouseEvent) {
        Person loggedInPerson = UserSectionController.getLoggedInPerson();
        try {
            BankAPI.getInstance().createAccount(loggedInPerson.getFirstName(), loggedInPerson.getLastName(), createAccountMenuUsernameField.getText(), createAccountMenuPasswordField.getText(), createAccountMenuRepeatPasswordField.getText());
            MessageTypeShow.showMessage(createAccountMenuInformationText,MessageTypeShow.SUCCESS,"Successfully created account");
        } catch (Exception e) {
            MessageTypeShow.showMessage(createAccountMenuInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }
}
