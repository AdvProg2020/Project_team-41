package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageOffsMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.Controller.UserSectionController.SellerController;
import Client.Models.Off;
import Client.Models.Product;
import Client.View.Menus.MessageType;
import Client.View.Menus.ProductPage.ProductPageGeneralButtons;
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

public class OffButtonController {
    public AnchorPane offNameAnchorPane;
    public GridPane gridPane;
    public TextField offNameTextField;
    public Button editOffButton;
    public Button showOffButton;
    ArrayList<String> offsShown = new ArrayList<>();

    public void editOffClicked(MouseEvent mouseEvent) throws IOException {
        if(offsShown.contains(offNameTextField.getText())){
            offsShown.remove(offNameTextField.getText());
            finishEditingOff();
        }
        else{
            offsShown.add(offNameTextField.getText());
            editOff();

        }
    }


    private void editOff() throws IOException {
        editOffController.globalViewOffsMenu = (AnchorPane) offNameAnchorPane.getParent().getParent().getParent().getParent().getParent();
        editOffController.globalOffName = offNameTextField.getText();
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().add(getIndexOfOff() + 1, App.loadFXML("userSection/sellerSection/viewOffsMenu/edit off"));
        AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(getIndexOfOff() + 1);
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
    private void finishEditingOff() {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        AnchorPane headOffMenu = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text informationText = (Text) headOffMenu.getChildren().get(1);

        AnchorPane editOffAnchorPane = (AnchorPane)vBox.getChildren().get(getIndexOfOff()+1);
        VBox editOffVBox = (VBox) editOffAnchorPane.getChildren().get(0);
        GridPane editOffGridPane = (GridPane) editOffVBox.getChildren().get(0);
        TextField editStartDateTextField = (TextField) editOffGridPane.getChildren().get(4);
        TextField editEndDateTextField = (TextField) editOffGridPane.getChildren().get(5);
        TextField editAmountOfDiscountTextField = (TextField) editOffGridPane.getChildren().get(6);
        TextField editProductsTextField = (TextField) editOffGridPane.getChildren().get(7);
        HashMap<String, String> edits = new HashMap<>();
        edits.put("start date", editStartDateTextField.getText());
        edits.put("end date", editEndDateTextField.getText());
        edits.put("amount of discount", editAmountOfDiscountTextField.getText());
        edits.put("products", editProductsTextField.getText());
        try {
            SellerController.getInstance().editOff(offNameTextField.getId(), edits);
        } catch (Exception e) {
            showMessage(informationText, MessageType.SUCCESS, "fields edited successfully");
        }
        vBox.getChildren().remove(getIndexOfOff()+1);

    }
    private int getIndexOfOff(){
        String offName = offNameTextField.getText();
        editOffController.globalViewOffsMenu = (AnchorPane) offNameAnchorPane.getParent().getParent().getParent().getParent().getParent();
        editOffController.globalOffName = offName;
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        for (int i = 0; i < vBox.getChildren().size(); i++) {
            AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(i);
            VBox innerVBox = (VBox) anchorPane.getChildren().get(0);
            GridPane gridPane = (GridPane) innerVBox.getChildren().get(0);
            if (gridPane.getChildren().get(0) instanceof Text)
                continue;
            TextField textField = (TextField) gridPane.getChildren().get(0);
            if (textField.getText().equals(offName)) {
                return i;
            }
        }
        return -2;
    }
    private void showMessage(Text text,MessageType messageType, String message){
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }

    public void showOffClicked(MouseEvent mouseEvent) {
        Off off = null;
        try {
            off = SellerController.getInstance().getOff(offNameTextField.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        ProductPageGeneralButtons.setTheProduct(off);
//        App.setRoot("ProductPage/ProductPageGeneral");



    }
}
