package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.UserSectionController;
import Client.View.Menus.MessageType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private String previousFirstNameTextField;
    private String previousLastNameTextField;
    private String previousEmailTextField;
    private String previousPhoneNumberTextField;


    @FXML
    public void initialize(){
        ArrayList<String> personalInfo = UserSectionController.getPersonalInfo(UserSectionController.getLoggedInPerson());
        String username = personalInfo.get(0).split(" ",2)[1];
        String firstName = personalInfo.get(1).split(" ",2)[1];
        String lastName = personalInfo.get(2).split(" ",2)[1];
        String credit = personalInfo.get(3).split(" ",2)[1];
        String email = personalInfo.get(4).split(" ",2)[1];
        String phoneNumber = personalInfo.get(5).split(" ",2)[1];
        userNameTextField.setText(username);
        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);
        creditTextField.setText(credit);
        emailTextField.setText(email);
        phoneNumberTextField.setText(phoneNumber);
        assignPreviousTextFields();
    }

    public void editButtonClicked(MouseEvent mouseEvent) {
        String edit;
        StringBuilder errors = new StringBuilder();
        edit = firstNameTextField.getText();
        int numberOfEdits = 0;
        int numberOfErrors = 0;
        try {
            if (!previousFirstNameTextField.equals(edit)) {
                UserSectionController.edit("firstName", edit);
                numberOfEdits++;
            }
        } catch (Exception e) {
            errors.append(e.getMessage()).append("-");
            numberOfErrors++;
            firstNameTextField.setPromptText(e.getMessage());
        }
        edit = lastNameTextField.getText();
        try {
            if (!previousLastNameTextField.equals(edit)) {
                UserSectionController.edit("lastName", edit);
                numberOfEdits++;
            }
        } catch (Exception e) {
            errors.append(e.getMessage()).append("-");
            numberOfErrors++;
            lastNameTextField.setPromptText(e.getMessage());
        }
        edit = emailTextField.getText();
        try {
            if (!previousEmailTextField.equals(edit)) {
                UserSectionController.edit("email", edit);
                numberOfEdits++;
            }
        } catch (Exception e) {
            errors.append(e.getMessage()).append("-");
            numberOfErrors++;
            emailTextField.setPromptText(e.getMessage());
        }
        edit = phoneNumberTextField.getText();
        try {
            if (!previousPhoneNumberTextField.equals(edit)) {
                UserSectionController.edit("phoneNumber", edit);
                numberOfEdits++;
            }
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
        if (numberOfEdits == 0) {
            showMessage(MessageType.INFORMATION,"no edits detected");
        }
        assignPreviousTextFields();

    }
    private void showMessage(MessageType messageType,String message){
        informationText.setFill(messageType.getLinearGradient());
        informationText.setText(message);

    }
    private void assignPreviousTextFields(){
                previousFirstNameTextField = firstNameTextField.getText();
                previousLastNameTextField = lastNameTextField.getText();
        previousEmailTextField = emailTextField.getText();
                previousPhoneNumberTextField = phoneNumberTextField.getText();
    }

}
