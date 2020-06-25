package Client.View.Menus.LoginRegister;

import Client.Controller.LoginRegisterController;
import Client.Models.Person.Buyer;
import Client.View.Menus.Menu;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class BuyerRegister {

    public TextField username;
    
    public TextField firstName;
    public TextField lastName;
    public TextField email;
    public TextField phoneNumber;
    public TextField money;
    public Label userError;
    public Label passError;
    public Label fNameError;
    public Label lNameError;
    public Label emailError;
    public Label phoneError;
    public Label moneyError;
    public PasswordField password;

    public void initialize() {
        setAllInvisible();
    }

    public void setAllInvisible() {
        userError.setVisible(false);
        passError.setVisible(false);
        fNameError.setVisible(false);
        lNameError.setVisible(false);
        emailError.setVisible(false);
        phoneError.setVisible(false);
        moneyError.setVisible(false);


    }

    public void register() {
        setAllInvisible();
        if (checkVariables()) {
            Buyer buyer=new Buyer();
            setVariables(buyer);
            try {
                LoginRegisterController.getInstance().createAccount(buyer);
                Menu.setRootForNewWindow("welcomeRegister");
            } catch (Exception e) {
                if (e.getMessage().equals("Invalid UserName!")) {
                    userError.setText("This username exists");
                    userError.setVisible(true);
                }
            }

        }


    }

    public boolean checkVariables() {
        if (username.getText().length() == 0) {
            userError.setText("It can't be empty");
            userError.setVisible(true);
            return false;
        } else if (password.getText().length() == 0) {
            passError.setVisible(true);
            return false;
        } else if (firstName.getText().length() == 0) {
            fNameError.setVisible(true);
            return false;
        } else if (lastName.getText().length() == 0) {
            lNameError.setVisible(true);
            return false;
        } else if (!email.getText().matches("\\S+@\\S+\\.\\S+")) {
            emailError.setVisible(true);
            return false;
        } else if (!phoneNumber.getText().matches("\\d+")) {
            phoneError.setVisible(true);
            return false;
        } else if (!money.getText().matches("\\d+")) {
            moneyError.setVisible(true);
            return false;
        } else if (Integer.parseInt(money.getText()) < 0) {
            moneyError.setVisible(true);
            return false;
        } else {
            return true;
        }
    }

    public void setVariables(Buyer buyer) {
        try {
            buyer.setUserName(username.getText());
            buyer.setPassword(password.getText());
            buyer.setFirstName(firstName.getText());
            buyer.setLastName(lastName.getText());
            buyer.setEmail(email.getText());
            buyer.setPhoneNumber(phoneNumber.getText());
            buyer.setCredit(Integer.parseInt(money.getText()));
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}

