package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.viewDiscountCodesMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.Models.Person.Person;
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
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class DiscountCodeButtonController {

    public GridPane gridPane;
    public Button viewDiscountCodeButton;
    public Button removeDiscountCodeButton;
    public TextField discountCodeTextField;
    public AnchorPane discountCodeAnchorPane;
    ArrayList<String> discountCodesShow = new ArrayList<>();

    public void viewDiscountCodeClicked(MouseEvent mouseEvent) throws IOException {
        if(discountCodesShow.contains(discountCodeTextField.getText())){
            discountCodesShow.remove(discountCodeTextField.getText());
            hideDiscountCode();
        }
        else{
            discountCodesShow.add(discountCodeTextField.getText());
            showDiscountCode();

        }
    }


    public void removeDiscountCodeClicked(MouseEvent mouseEvent) throws Exception {

        String discountCode = discountCodeTextField.getText();
        ManagerController.getInstance().removeDiscountCode(discountCode);
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        if(discountCodesShow.contains(discountCodeTextField.getText()))
            vBox.getChildren().remove(getIndexOfUser()+1);
        vBox.getChildren().remove(getIndexOfUser());
        AnchorPane anchorPane = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text text = (Text) anchorPane.getChildren().get(2);
        showMessage(text,MessageType.SUCCESS,"successfully removed discount code");


    }
    private void showDiscountCode() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().add(getIndexOfUser() + 1, App.loadFXML("userSection/managerSection/viewDiscountCodesMenu/view discount code"));
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
    private void hideDiscountCode() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        HashMap<String, String> edits = new HashMap<>();

        AnchorPane headDiscountCodeMenu = (AnchorPane) gridPane.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        Text informationText = (Text) headDiscountCodeMenu.getChildren().get(2);

        AnchorPane editDiscountCodeAnchorPane = (AnchorPane) vBox.getChildren().get(getIndexOfUser() + 1);
        VBox editDiscountCodeVBox = (VBox) editDiscountCodeAnchorPane.getChildren().get(0);
        GridPane editDiscountCodeGridPane = (GridPane) editDiscountCodeVBox.getChildren().get(0);
        TextField editStartDateTextField = (TextField) editDiscountCodeGridPane.getChildren().get(8);
        TextField editEndDateTextField = (TextField) editDiscountCodeGridPane.getChildren().get(9);
        TextField editDiscountPercentageTextField = (TextField) editDiscountCodeGridPane.getChildren().get(10);
        TextField editMaximumDiscountTextField = (TextField) editDiscountCodeGridPane.getChildren().get(11);
        TextField editDiscountRepeatsTextField = (TextField) editDiscountCodeGridPane.getChildren().get(12);
        TextField editPeopleWhoCanUseItTextField = (TextField) editDiscountCodeGridPane.getChildren().get(13);
        edits.put("start date", editStartDateTextField.getText());
        edits.put("end date", editEndDateTextField.getText());
        edits.put("discount percentage", editDiscountPercentageTextField.getText());
        edits.put("maximum discount", editMaximumDiscountTextField.getText());
        edits.put("discount repeats for each user", editDiscountRepeatsTextField.getText());
        edits.put("people who can use it", editPeopleWhoCanUseItTextField.getText());
        try {
            ManagerController.getInstance().editDiscountCode(discountCodeTextField.getText(), edits);
            showMessage(informationText, MessageType.SUCCESS, "fields edited successfully");
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(informationText, MessageType.ERROR, e.getMessage());
        }


        vBox.getChildren().remove(getIndexOfUser() + 1);

    }
    private int getIndexOfUser(){
        String discountCode = discountCodeTextField.getText();
        ViewDiscountCodeController.code = discountCode;
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        for (int i = 0; i < vBox.getChildren().size(); i++) {
            AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(i);
            VBox innerVBox = (VBox) anchorPane.getChildren().get(0);
            GridPane gridPane = (GridPane) innerVBox.getChildren().get(0);
            if (gridPane.getChildren().get(0) instanceof Text)
                continue;
            TextField textField = (TextField) gridPane.getChildren().get(0);
            if (textField.getText().equals(discountCodeTextField.getText())) {
                return i;
            }
        }
        return -2;
    }
    private void showMessage(Text text,MessageType messageType, String message){
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }

}