package Client.View.Menus.UserSectionMenus.ManagerSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import Client.Models.Person.Person;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;
import java.io.IOException;
import java.util.ArrayList;

public class userButtonController {

    public AnchorPane usernameAnchorPane;
    public Button removeUserButton;
    public Button viewUserButton;
    public GridPane gridPane;
    public TextField usernameTextField;
    ArrayList<String> usersShown = new ArrayList<>();

    public void viewUserClicked(MouseEvent mouseEvent) throws IOException {
        if(usersShown.contains(usernameTextField.getText())){
            usersShown.remove(usernameTextField.getText());
            hideUser();
        }
        else{
            usersShown.add(usernameTextField.getText());
            showUser();
        }
    }

    public void removeUserClicked(MouseEvent mouseEvent) {

    }
    private void showUser() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().add(getIndexOfUser() + 1, App.loadFXML("userSection/managerSection/view user info"));
    }
    private void hideUser() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().remove(getIndexOfUser()+1);
    }
    private int getIndexOfUser(){
        Person user = null;
        try {
            user = ManagerController.getInstance().getUserByUsername(usernameTextField.getText().split(":")[1].trim());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        viewUserInfoController.user = user;
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        for (int i = 0; i < vBox.getChildren().size(); i++) {
            AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(i);
            VBox innerVBox = (VBox) anchorPane.getChildren().get(0);
            GridPane gridPane = (GridPane) innerVBox.getChildren().get(0);
            if (gridPane.getChildren().get(0) instanceof Text)
                continue;
            TextField textField = (TextField) gridPane.getChildren().get(0);
            if (textField.getText().equals(usernameTextField.getText())) {
                return i;
            }
        }
        return -2;
    }
}
