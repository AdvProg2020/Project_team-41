package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.manageRequestsMenu;

import Client.Controller.UserSectionController.ManagerController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;

public class ViewRequestController {
    static String requestId;
    public AnchorPane requestInformation;
    public VBox requestInformationVBox;

    @FXML
    public void initialize(){
        ArrayList<String> requestDetails = null;
        try {
            requestDetails = ManagerController.getInstance().getRequestDetails(requestId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String requestDetail : requestDetails) {
            makeRequestDetailsTextField(requestDetail);
        }
    }
    private void makeRequestDetailsTextField(String requestDetail) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/managerSection/manageRequestsMenu/text field for request details");
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextField textField = (TextField) root;
        textField.setText(requestDetail);

        requestInformationVBox.getChildren().add(root);
    }

}
