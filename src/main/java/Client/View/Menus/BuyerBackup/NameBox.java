package Client.View.Menus.BuyerBackup;

import javafx.scene.control.Label;

public class NameBox {
    public static String user;
    public Label username;
    public void initialize(){
        username.setText(user);
    }
}
