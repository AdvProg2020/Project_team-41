package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.manageRequestsMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageTypeShow;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class ManageRequestsMenu {
    public Text informationText;
    public VBox requestsVBox;
    public ScrollPane requestsScrollPane;

    @FXML
    public void initialize(){
        try {
            if(ManagerController.getInstance().showRequest().isEmpty())
                showMessage(informationText, MessageTypeShow.INFORMATION,"No requests found");
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.ERROR,e.getMessage());
        }
        try {
            for (String request : ManagerController.getInstance().showRequest()) {
                makeRequestSplitButton(request);
            }
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.ERROR,e.getMessage());
        }
    }

    private void makeRequestSplitButton(String request) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/managerSection/manageRequestsMenu/requestSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        VBox vBox = (VBox) anchorPane.getChildren().get(0);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        TextField textField = (TextField) gridPane.getChildren().get(0);
        textField.setText(request);

        requestsVBox.getChildren().add(root);
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }

}
