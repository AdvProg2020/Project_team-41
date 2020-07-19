package Client.View.Menus.LoginRegister;

import Client.Controller.LoginRegisterController;
import Client.Controller.UserSectionController.ManagerController;
import Client.Models.Person.Manager;
import Client.View.Menus.Menu;
import Client.View.Menus.UserSectionMenus.ManagerSectionMenus.manageCategoryMenu.ManageCategoriesMenu;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
    public TextField wage;
    public Label wageError;
    public Label wageTitle;
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
            wage.setVisible(false);
            wageTitle.setVisible(false);

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
        wageError.setVisible(false);
        welcome1.setVisible(false);


    }
    public void register() throws IOException {
        setAllInvisible();
        if(checkVariables()){
            Manager manager=new Manager();
            setVariables(manager);
            try {
                if(!managerExists) {
                    ManagerController.getInstance().setUpManagerAccountId(manager, bankUsername.getText(), bankPass.getText());
                    ManagerController.getInstance().setWage(Integer.parseInt(wage.getText()));
                }
                LoginRegisterController.getInstance().createAccount(manager);
                welcome1.setText("Account successfully created");
                welcome1.setTextFill(Color.GREEN);
                welcome1.setVisible(true);
                clearVariables();
                Menu.closeWindow();
//                Menu.setRootForNewWindow("welcomeRegister");
            } catch (Exception e) {
                if(e.getMessage().equals("Invalid UserName!")){
                    userError.setText("This username exists");
                    userError.setVisible(true);
                }else{
                    welcome1.setTextFill(Color.RED);
                    welcome1.setText(e.getMessage());
                    welcome1.setVisible(true);
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
        }else if(!managerExists && !wage.getText().matches("\\d+")){
            wageError.setVisible(true);
            return false;
        }else if(!managerExists && (Integer.parseInt(wage.getText())<0||Integer.parseInt(wage.getText())>100)){
            wageError.setVisible(true);
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
        wage.clear();
    }
}