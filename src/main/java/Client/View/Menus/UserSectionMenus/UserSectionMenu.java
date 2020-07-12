package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.UserSectionController;
import Client.View.Menus.MessageType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class UserSectionMenu {

    public TextField userNameTextField;
    public TextField firstNameTextField;
    public TextField creditTextField;
    public TextField lastNameTextField;
    public TextField emailTextField;
    public TextField phoneNumberTextField;
    public Text informationText;

    @FXML
    public void initialize(){
        ArrayList<String> personalInfo = UserSectionController.getPersonalInfo(UserSectionController.getLoggedInPerson());
        String username = personalInfo.get(0).split(" ")[1];
        String firstName = personalInfo.get(1).split(" ")[1];
        String lastName = personalInfo.get(2).split(" ")[1];
        String credit = personalInfo.get(3).split(" ")[1];
        String email = personalInfo.get(4).split(" ")[1];
        String phoneNumber = personalInfo.get(5).split(" ")[1];
        userNameTextField.setText(username);
        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);
        creditTextField.setText(credit);
        emailTextField.setText(email);
        phoneNumberTextField.setText(phoneNumber);
    }

    public void editButtonClicked(MouseEvent mouseEvent) {
        String edit;
        StringBuilder errors = new StringBuilder();
        edit = firstNameTextField.getText();
        int numberOfEdits = 0;
        int numberOfErrors = 0;
        try {
            UserSectionController.edit("firstName", edit);
            numberOfEdits++;
        } catch (Exception e) {
            errors.append(e.getMessage()).append("-");
            numberOfErrors++;
            firstNameTextField.setPromptText(e.getMessage());
        }
        edit = lastNameTextField.getText();
        try {
            UserSectionController.edit("lastName", edit);
            numberOfEdits++;
        } catch (Exception e) {
            errors.append(e.getMessage()).append("-");
            numberOfErrors++;
            lastNameTextField.setPromptText(e.getMessage());
        }
        edit = emailTextField.getText();
        try {
            UserSectionController.edit("email", edit);
            numberOfEdits++;
        } catch (Exception e) {
            errors.append(e.getMessage()).append("-");
            numberOfErrors++;
            emailTextField.setPromptText(e.getMessage());
        }
        edit = phoneNumberTextField.getText();
        try {
            UserSectionController.edit("phoneNumber", edit);
            numberOfEdits++;
        } catch (Exception e) {
            errors.append(e.getMessage()).append("-");
            numberOfErrors++;
            phoneNumberTextField.setPromptText(e.getMessage());
        }
        if(numberOfErrors == 0){
            showMessage(MessageType.SUCCESS,"edited successfully");
        }
        else{
            showMessage(MessageType.ERROR, errors.substring(0, errors.length() - 1));
        }

    }
    private void showMessage(MessageType messageType,String message){
        informationText.setFill(messageType.getLinearGradient());
        informationText.setText(message);

    }

}
