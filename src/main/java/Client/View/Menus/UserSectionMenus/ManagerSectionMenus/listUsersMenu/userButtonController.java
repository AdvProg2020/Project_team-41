package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.listUsersMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.Models.Person.Person;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
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
import java.util.Timer;
import java.util.TimerTask;

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


    public void removeUserClicked(MouseEvent mouseEvent) throws Exception {

        AnchorPane anchorPane = (AnchorPane) NodeFinder.getParentById(gridPane,"usersAnchorPane");
        Text text = (Text) anchorPane.getChildren().get(2);
        Person user = null;
        try {
            user = ManagerController.getInstance().getUserByUsername(usernameTextField.getText().split(":")[1].trim());
            if (user.equals(ManagerController.getLoggedInPerson())) {
                showMessage(text, MessageTypeShow.ERROR,"cannot remove current user");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ManagerController.getInstance().deleteUser(user.getUserName());
        VBox vBox = (VBox) NodeFinder.getParentById(gridPane,"usersVBox");
        if(usersShown.contains(usernameTextField.getText()))
            vBox.getChildren().remove(getIndexOfUser()+1);
        vBox.getChildren().remove(getIndexOfUser());
        showMessage(text, MessageTypeShow.SUCCESS,"successfully removed user");


    }
    private void showUser() throws IOException {
        VBox vBox = (VBox) NodeFinder.getParentById(gridPane,"usersVBox");
        vBox.getChildren().add(getIndexOfUser() + 1, App.loadFXML("userSection/managerSection/listUsersMenu/view user info"));
        AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(getIndexOfUser()+1);
        VBox vBox1 = (VBox) anchorPane.getChildren().get(0);
        double anchorPaneHeight = anchorPane.getPrefHeight();
        double vBoxHeight = vBox1.getPrefHeight();
        anchorPane.setPrefHeight(0);
        anchorPane.setMaxHeight(0);
        vBox1.setPrefHeight(0);
        vBox1.setMaxHeight(0);

        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(new TimerTask() {
            int i=0;

            @Override
            public void run() {
                if (i<100) {
                    anchorPane.setPrefHeight(anchorPane.getPrefHeight()+anchorPaneHeight/100);
                    anchorPane.setMaxHeight(anchorPane.getMaxHeight()+anchorPaneHeight/100);
                    vBox1.setPrefHeight(vBox1.getPrefHeight()+vBoxHeight/100);
                    vBox1.setMaxHeight(vBox1.getMaxHeight()+vBoxHeight/100);

                } else {
                    this.cancel();
                }
                i++;
            }

        }, 0, 5);

    }
    private void hideUser() throws IOException {
        VBox vBox = (VBox) NodeFinder.getParentById(gridPane,"usersVBox");
        vBox.getChildren().remove(getIndexOfUser()+1);
    }
    private int getIndexOfUser(){
        Person user = null;
        try {
            user = ManagerController.getInstance().getUserByUsername(usernameTextField.getText().split(":")[1].trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewUserInfoController.user = user;
        VBox vBox = (VBox) NodeFinder.getParentById(gridPane,"usersVBox");
        for (int i = 0; i < vBox.getChildren().size(); i++) {
            AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(i);
            VBox innerVBox = (VBox) anchorPane.getChildren().get(0);
            GridPane gridPane = (GridPane) innerVBox.getChildren().get(0);
            if (gridPane.getChildren().get(0) instanceof Text)
                continue;
            TextField textField = (TextField) NodeFinder.getChildById(gridPane, "usernameTextField");
            if (textField.getText().equals(usernameTextField.getText())) {
                return i;
            }
        }
        return -2;
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
}
