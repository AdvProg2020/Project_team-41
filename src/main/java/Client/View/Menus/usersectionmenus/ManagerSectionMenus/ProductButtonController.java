package Client.View.Menus.UserSectionMenus.ManagerSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import Client.Models.Product;
import Client.View.Menus.MessageType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProductButtonController {
    public AnchorPane productNameAnchorPane;
    public TextField productNameTextField;
    public Button removeProductButton;
    public Button viewProductButton;
    public GridPane gridPane;
    public TextField productIdTextField;
    ArrayList<String> productsShown = new ArrayList<>();

    public void viewProductClicked(MouseEvent mouseEvent) throws IOException {
        if(productsShown.contains(productIdTextField.getText())){
            productsShown.remove(productIdTextField.getText());
            hideProduct();
        }
        else{
            productsShown.add(productIdTextField.getText());
            showProduct();

        }
    }


    public void removeProductClicked(MouseEvent mouseEvent) throws Exception {

        try {
            ManagerController.getInstance().removeProduct(productIdTextField.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        if(productsShown.contains(productIdTextField.getText()))
            vBox.getChildren().remove(getIndexOfProduct()+1);
        vBox.getChildren().remove(getIndexOfProduct());
        AnchorPane anchorPane = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text text = (Text) anchorPane.getChildren().get(1);
        showMessage(text, MessageType.SUCCESS,"successfully removed product");


    }
    private void showProduct() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        //vBox.getChildren().add(getIndexOfProduct() + 1, App.loadFXML("userSection/managerSection/view user info"));
        AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(getIndexOfProduct()+1);
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
    private void hideProduct() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().remove(getIndexOfProduct()+1);
    }
    private int getIndexOfProduct(){
        String productId = productIdTextField.getText();
        Product product;
        for (Product oneProduct : ManagerController.getInstance().getAllProducts()) {
            if(oneProduct.getProductId().equals(productId))
                product = oneProduct;
        }
        //viewProductController.product = product;
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        for (int i = 0; i < vBox.getChildren().size(); i++) {
            AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(i);
            VBox innerVBox = (VBox) anchorPane.getChildren().get(0);
            GridPane gridPane = (GridPane) innerVBox.getChildren().get(0);
            if (gridPane.getChildren().get(1) instanceof Text)
                continue;
            TextField textField = (TextField) gridPane.getChildren().get(1);
            if (textField.getText().equals(productIdTextField.getText())) {
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
