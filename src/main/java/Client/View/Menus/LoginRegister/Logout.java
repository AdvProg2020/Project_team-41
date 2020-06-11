package Client.View.Menus.LoginRegister;

import Client.Controller.LoginRegisterController;
import Client.View.Menus.Menu;
import org.example.App;

import java.io.IOException;

public class Logout {
    public static String father;
    public void yes() throws IOException {
        LoginRegisterController.getInstance().logout();
        App.setRoot(Logout.father);
        Menu.closeWindow();
    }
    public void no(){
        Menu.closeWindow();
    }
}
