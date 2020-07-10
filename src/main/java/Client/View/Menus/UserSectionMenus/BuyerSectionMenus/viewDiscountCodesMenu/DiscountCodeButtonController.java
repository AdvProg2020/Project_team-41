package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewDiscountCodesMenu;

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

public class DiscountCodeButtonController {

    public GridPane gridPane;
    public Button viewDiscountCodeButton;
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


    private void showDiscountCode() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().add(getIndexOfUser() + 1, App.loadFXML("userSection/buyerSection/showDiscountCodesMenu/view discount code"));
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
    private void hideDiscountCode() {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
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

}
