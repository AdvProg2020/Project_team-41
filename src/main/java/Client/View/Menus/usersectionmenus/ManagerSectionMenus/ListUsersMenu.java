package Client.View.Menus.UserSectionMenus.ManagerSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class ListUsersMenu {

    public Text informationText;
    public VBox usersVBox;
    public SplitMenuButton userButton;

    @FXML
    public void initialize(){
        for (String user : ManagerController.getInstance().getAllUsers()) {
            usersVBox.getChildren().add(makeUserSplitButton(user));
        }
    }

    public void createManagerProfileClicked(MouseEvent mouseEvent) {

    }
    private SplitMenuButton makeUserSplitButton(String username){
        SplitMenuButton splitMenuButton = new SplitMenuButton();

        splitMenuButton.setText(username);
        MenuItem choice1 = new MenuItem("view");
        MenuItem choice2 = new MenuItem("remove");
        choice1.setOnAction((e)-> {
            removeUserClicked(choice1.getParentMenu().getText());
        });
        choice2.setOnAction((e)-> {
            viewUserClicked(choice2.getParentMenu().getText());
        });
        splitMenuButton.getItems().addAll(choice1, choice2);
        Parent root = null;
            try {
                root = App.loadFXML("userSection/managerSection/userSplitButton");
            } catch (IOException e) {
                e.printStackTrace();
            }

            usersVBox.getChildren().add(root);
        return splitMenuButton;
    }
    private void removeUserClicked(String username){

    }
    private void viewUserClicked(String username){

    }
}
