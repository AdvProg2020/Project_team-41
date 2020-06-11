package Client.View.Menus.LoginRegister;

import Client.Controller.LoginRegisterController;
import Client.View.Menus.Menu;
import javafx.scene.control.Label;

import java.io.IOException;

public class UserType {
    public Label managerError;
    public void initialize(){
        managerError.setVisible(false);
    }
    public void manager() throws IOException {
        if(LoginRegisterController.getInstance().checkIfManagerExists()){
            managerError.setVisible(true);
        }else{
            Menu.setRoot("managerRegister");
        }
    }
    public void seller() throws IOException {
        Menu.setRoot("sellerRegister");
    }
    public void buyer() throws IOException {
        Menu.setRoot("buyerRegister");

    }
}
