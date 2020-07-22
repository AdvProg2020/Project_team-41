package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.listUsersMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class ListUsersMenu {
    public VBox usersVBox;
    public ScrollPane listUsersScrollPane;
    public Text informationText;

    @FXML
    public void initialize(){

        try {
            for (String user : ManagerController.getInstance().getAllUsers()) {
                    makeUserSplitButton(user.split("-")[0],user.split("-")[1]);
            }
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.ERROR,e.getMessage());
        }
    }

    public void createManagerProfileClicked(MouseEvent mouseEvent) throws IOException {
        BorderPane managerSectionBorderPane = (BorderPane) NodeFinder.getParentById(usersVBox, "managerSectionBorderPane");
        Parent root = null;
        root = App.loadFXML("managerRegister");
        managerSectionBorderPane.setCenter(root);
    }
    private void makeUserSplitButton(String username,String online) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/managerSection/listUsersMenu/userSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        VBox vBox = (VBox) anchorPane.getChildren().get(0);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        TextField textField = (TextField) gridPane.getChildren().get(0);
        textField.setText(username);
        Circle onlineCircle = (Circle) NodeFinder.getChildById(root, "onlineCircle");
        if (online.trim().equals("true")) {
            onlineCircle.setFill(Paint.valueOf("#6e9470"));
        }
        usersVBox.getChildren().add(root);
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }

}
