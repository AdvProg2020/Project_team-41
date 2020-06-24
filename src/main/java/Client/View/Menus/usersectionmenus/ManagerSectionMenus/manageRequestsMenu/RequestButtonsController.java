package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.manageRequestsMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageType;
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

public class RequestButtonsController {
    public GridPane gridPane;
    public AnchorPane requestAnchorPane;
    public Button declineRequestButton;
    public Button viewRequestButton;
    public TextField requestIdTextField;
    public Button acceptRequestButton;
    ArrayList<String> requestsShown = new ArrayList<>();

    public void viewRequestClicked(MouseEvent mouseEvent) throws IOException {
        if(requestsShown.contains(requestIdTextField.getText())){
            requestsShown.remove(requestIdTextField.getText());
            hideRequest();
        }
        else{
            requestsShown.add(requestIdTextField.getText());
            showRequest();

        }
    }


    public void declineRequestClicked(MouseEvent mouseEvent) throws Exception {

        ManagerController.getInstance().declineRequest(getRequestId(requestIdTextField.getText()));
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        if(requestsShown.contains(requestIdTextField.getText()))
            vBox.getChildren().remove(getIndexOfRequest()+1);
        vBox.getChildren().remove(getIndexOfRequest());
        AnchorPane anchorPane = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text text = (Text) anchorPane.getChildren().get(1);
        showMessage(text, MessageType.SUCCESS,"successfully declined request");


    }
    private void showRequest() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().add(getIndexOfRequest() + 1, App.loadFXML("userSection/managerSection/manageRequestsMenu/view request info"));
        AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(getIndexOfRequest()+1);
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
    private void hideRequest() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().remove(getIndexOfRequest()+1);
    }
    private int getIndexOfRequest(){
        String requestId = null;
        try {
            requestId = getRequestId(requestIdTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ViewRequestController.requestId = requestId;
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        for (int i = 0; i < vBox.getChildren().size(); i++) {
            AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(i);
            VBox innerVBox = (VBox) anchorPane.getChildren().get(0);
            GridPane gridPane = (GridPane) innerVBox.getChildren().get(0);
            if (gridPane.getChildren().get(0) instanceof Text)
                continue;
            TextField textField = (TextField) gridPane.getChildren().get(0);
            if (getRequestId(textField.getText()).equals(getRequestId(requestIdTextField.getText()))) {
                return i;
            }
        }
        return -2;
    }
    private void showMessage(Text text,MessageType messageType, String message){
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }
    private String getRequestId(String rawRequestId){
        return rawRequestId.split(":")[0].trim();
    }

    public void acceptRequestClicked(MouseEvent mouseEvent) {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        AnchorPane anchorPane = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text text = (Text) anchorPane.getChildren().get(1);
        try {
            ManagerController.getInstance().acceptRequest(getRequestId(requestIdTextField.getText()));
            showMessage(text, MessageType.SUCCESS, "successfully accepted request");
            if (requestsShown.contains(requestIdTextField.getText()))
                vBox.getChildren().remove(getIndexOfRequest() + 1);
            vBox.getChildren().remove(getIndexOfRequest());
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(text, MessageType.ERROR, e.getMessage());
        }

    }
}
