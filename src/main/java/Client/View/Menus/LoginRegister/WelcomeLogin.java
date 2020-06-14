package Client.View.Menus.LoginRegister;

import Client.View.Menus.Menu;
import org.example.App;

import java.io.IOException;

public class WelcomeLogin {

    public void initialize() {
        Menu.window.setOnCloseRequest(e -> {
            try {
                ok();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
    public void ok() throws IOException {
        Menu.closeWindow();
       App.setRoot(LoginForm.father);
    }
}
