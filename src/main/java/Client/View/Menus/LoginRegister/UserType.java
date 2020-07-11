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
        try {
            if(LoginRegisterController.getInstance().checkIfManagerExists()){
                managerError.setVisible(true);
            }else{
                Menu.setRootForNewWindow("managerRegister");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void seller() throws IOException {
        Menu.setRootForNewWindow("sellerRegister");
    }
    public void buyer() throws IOException {
        Menu.setRootForNewWindow("buyerRegister");

    }
}
