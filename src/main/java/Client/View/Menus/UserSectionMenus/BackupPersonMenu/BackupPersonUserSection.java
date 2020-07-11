package Client.View.Menus.UserSectionMenus.BackupPersonMenu;

import Client.Controller.UserSectionController.UserSectionController;
import Client.View.Menus.Menu;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import org.example.App;

import java.io.IOException;

public class BackupPersonUserSection extends Menu {
    public ScrollPane usersScrollPane;
    public Button loginLogout;
    public AnchorPane nameAnchorPane;
    public AnchorPane sendAnchorPane;
    public ScrollPane chatScrollPane;
    public static String parentFxmlAddress;


    public void initialize() {
        if(UserSectionController.getLoggedInPerson()==null){
            loginLogout.setText("Register/Login");
        }else{
            loginLogout.setText("Logout");
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        App.setRoot(parentFxmlAddress);
    }

    public void registerOrLogin(ActionEvent actionEvent) throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("mainMenu");
        }else{
            logout("mainMenu");
        }
    }
}
