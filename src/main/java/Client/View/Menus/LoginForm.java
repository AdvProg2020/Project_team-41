package Client.View.Menus;

import Client.Controller.LoginRegisterController;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class LoginForm {
    public javafx.scene.control.TextField username;
    public PasswordField password;
    public Label userError;
    public Label passError;


    public void initialize(){
        userError.setVisible(false);
        passError.setVisible(false);
    }
    public void login(){
        userError.setVisible(false);
        passError.setVisible(false);
        try {
            LoginRegisterController.getInstance().login(username.getText(), password.getText());
        } catch (Exception e) {
            if(e.getMessage().equals("Invalid Password")){
                passError.setVisible(true);
            }else if(e.getMessage().equals("Invalid UserName")){
                userError.setVisible(true);
            }

        }

    }
    public void register(){

    }
}
