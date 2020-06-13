package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.listUsersMenu;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Person;
//import animatefx.animation.AnimationFX;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class viewUserInfoController {
    static Person user;
    public AnchorPane userInformation;
    public TextField userNameTextField;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField creditTextField;
    public TextField emailTextField;
    public TextField phoneNumberTextField;

    @FXML
    public void initialize(){
        ArrayList<String> personalInfo = UserSectionController.getPersonalInfo(user);
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


}
