package Client.View.Menus.LoginRegister;

import Client.Controller.LoginRegisterController;
import Client.Models.Person.Manager;
import Client.View.Menus.Menu;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class ManagerRegister {
    public TextField username;

    public TextField firstName;
    public TextField lastName;
    public TextField email;
    public TextField phoneNumber;
    public Label userError;
    public Label passError;
    public Label fNameError;
    public Label lNameError;
    public Label emailError;
    public Label phoneError;
    public Label welcome1;
    public PasswordField password;
    public TextField bankUsername;
    public Label bankUsernameTitle;
    public Label bankUsernameError;
    public TextField bankPass;
    public Label bankPassTitle;
    public Label bankPassError;
    boolean managerExists;

    public void initialize(){
        setAllInvisible();
        managerExists = false;
        try {
            managerExists=LoginRegisterController.getInstance().checkIfManagerExists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(managerExists){
            bankUsername.setVisible(false);
            bankUsernameTitle.setVisible(false);
            bankPass.setVisible(false);
            bankPassTitle.setVisible(false);

        }
    }

    public void setAllInvisible(){
        userError.setVisible(false);
        passError.setVisible(false);
        fNameError.setVisible(false);
        lNameError.setVisible(false);
        emailError.setVisible(false);
        phoneError.setVisible(false);
        bankPassError.setVisible(false);
        bankUsernameError.setVisible(false);
        welcome1.setVisible(false);


    }
    public void register() throws IOException {
        setAllInvisible();
        if(checkVariables()){
            Manager manager=new Manager();
            setVariables(manager);
            try {
                LoginRegisterController.getInstance().createAccount(manager);
                welcome1.setVisible(true);
                clearVariables();
                Menu.closeWindow();
//                Menu.setRootForNewWindow("welcomeRegister");
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
        }else if(!managerExists && bankUsername.getText().length()==0){
            bankUsernameError.setVisible(true);
            return false;
        }else if(!managerExists&& bankPass.getText().length()==0){
            bankPassError.setVisible(true);
            return false;
        }
        else{

            return true;
        }
    }
    public void setVariables(Manager manager){
        try {
            manager.setUserName(username.getText());
            manager.setPassword(password.getText());
            manager.setFirstName(firstName.getText());
            manager.setLastName(lastName.getText());
            manager.setEmail(email.getText());
            manager.setPhoneNumber(phoneNumber.getText());
            if(!managerExists){
                manager.setUpManagerAccountId(manager,bankUsername.getText(),bankPass.getText());
            }
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
        bankUsername.clear();
        bankPass.clear();
    }
}