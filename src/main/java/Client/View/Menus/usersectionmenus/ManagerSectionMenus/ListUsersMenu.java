package Client.View.Menus.UserSectionMenus.ManagerSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class ListUsersMenu {
    public VBox usersVBox;
    public ScrollPane listUsersScrollPane;
    public Text informationText;

    @FXML
    public void initialize(){
        listUsersScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        listUsersScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for (String user : ManagerController.getInstance().getAllUsers()) {
                makeUserSplitButton(user);
        }
    }

    public void createManagerProfileClicked(MouseEvent mouseEvent) {

    }
    private void makeUserSplitButton(String username) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/managerSection/userSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        VBox vBox = (VBox) anchorPane.getChildren().get(0);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        TextField textField = (TextField) gridPane.getChildren().get(0);
        textField.setText(username);

        usersVBox.getChildren().add(root);
    }

}
