package Client.View.Menus.BuyerBackup;

import javafx.scene.control.Label;
import javafx.scene.input.TouchEvent;

public class NameOfUser {
    public static String user;
    public Label username;

    public void initialize(){
        username.setText(user);
    }

    public void openChat(TouchEvent touchEvent) {
    }
}
