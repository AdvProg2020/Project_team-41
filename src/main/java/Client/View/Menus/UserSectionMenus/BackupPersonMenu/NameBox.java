package Client.View.Menus.UserSectionMenus.BackupPersonMenu;

import javafx.scene.control.Label;

public class NameBox {
    public static String user;
    public Label username;
    public void initialize(){
        username.setText(user);
    }
}
