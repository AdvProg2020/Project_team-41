package Client.View.Menus;

import Client.Controller.UserSectionController.UserSectionController;
import javafx.scene.control.Button;

import java.io.IOException;

public class gMainMenu extends Menu {

    public Button loginLogout;

    public void initialize(){
        if(UserSectionController.getLoggedInPerson()==null){
            loginLogout.setText("Register/Login");
        }else{
            loginLogout.setText("Logout");
        }
    }


    public void registerOrLogin() throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("mainMenu");
        }else{
            logout("mainMenu");
        }
    }


}
