package Client.View.Menus.LoginRegister;

import Client.Controller.LoginRegisterController;
import Client.Models.Person.BackupPerson;
import Client.Models.Person.Manager;
import Client.View.Menus.Menu;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class BackupPersonRegister {
    public TextField username;
    public Label userError;
    public Label passError;
    public PasswordField password;
    public TextField firstName;
    public Label fNameError;
    public TextField lastName;
    public Label lNameError;
    public TextField email;
    public Label emailError;
    public TextField phoneNumber;
    public Label phoneError;
    public Label welcome1;

    public void initialize(){
        setAllInvisible();
    }
    public void setAllInvisible(){
        userError.setVisible(false);
        passError.setVisible(false);
        fNameError.setVisible(false);
        lNameError.setVisible(false);
        emailError.setVisible(false);
        phoneError.setVisible(false);
        welcome1.setVisible(false);
    }
    public void register(ActionEvent actionEvent) {
        setAllInvisible();
        if(checkVariables()){
            BackupPerson backupPerson=new BackupPerson();
            setVariables(backupPerson);
            try {
                LoginRegisterController.getInstance().createAccount(backupPerson);
                welcome1.setVisible(true);
                clearVariables();
            } catch (Exception e) {
                if(e.getMessage().equals("Invalid UserName!")){
                    userError.setText("This username exists");
                    userError.setVisible(true);
                }
            }

        }
    }
    public boolean checkVariables(){
        if(username.getText().length()==0){
            userError.setText("It can't be empty");
            userError.setVisible(true);
            return false;
        }else if(password.getText().length()==0){
            passError.setVisible(true);
            return false;
        }else if(firstName.getText().length()==0){
            fNameError.setVisible(true);
            return false;
        }else if(lastName.getText().length()==0){
            lNameError.setVisible(true);
            return false;
        }
        else if(!email.getText().matches("\\S+@\\S+\\.\\S+")){
            emailError.setVisible(true);
            return false;
        }else if(!phoneNumber.getText().matches("\\d+")){
            phoneError.setVisible(true);
            return false;
        }else{
            return true;
        }
    }
    public void setVariables(BackupPerson backupPerson){
        try {
            backupPerson.setUserName(username.getText());
            backupPerson.setPassword(password.getText());
            backupPerson.setFirstName(firstName.getText());
            backupPerson.setLastName(lastName.getText());
            backupPerson.setEmail(email.getText());
            backupPerson.setPhoneNumber(phoneNumber.getText());
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
    public void clearVariables(){
        username.clear();
        password.clear();
        firstName.clear();
        lastName.clear();
        email.clear();
        phoneNumber.clear();
    }
}
