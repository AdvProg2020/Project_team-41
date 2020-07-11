package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageProductsMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.Models.Product;
import Client.Models.SpecialFeature;
import Client.View.Menus.MessageTypeShow;
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

public class ProductButtonController {
    public AnchorPane productNameAnchorPane;
    public TextField productNameTextField;
    public Button removeProductButton;
    public Button viewProductButton;
    public GridPane gridPane;
    public TextField productIdTextField;
    public Button editProductButton;
    ArrayList<String> productsShown = new ArrayList<>();

    public void viewProductClicked(MouseEvent mouseEvent) throws Exception {
            showProduct();
    }


    public void removeProductClicked(MouseEvent mouseEvent) throws Exception {

        try {
            SellerController.getInstance().removeProduct(productIdTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        if(productsShown.contains(productIdTextField.getText()))
            vBox.getChildren().remove(getIndexOfProduct()+1);
        vBox.getChildren().remove(getIndexOfProduct());
        AnchorPane anchorPane = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text text = (Text) anchorPane.getChildren().get(1);
        showMessage(text, MessageTypeShow.SUCCESS,"your request will be processed");


    }
    private void showProduct() throws Exception {
        Product product = null;
        try {
            product = SellerController.getInstance().getProduct(productIdTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProductPageGeneralButtons.setTheProduct(product);
        ProductPageGeneralButtons.getTheProduct().setViews(ProductPageGeneralButtons.getTheProduct().getViews()+1);
        App.setRoot("ProductPage/ProductPageGeneral");
        ProductPageGeneralButtons.parentFxmlAddress = "userSection/sellerSection/seller section";
    }

    private void hideProduct() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().remove(getIndexOfProduct()+1);
    }
    //
    public void editProductClicked(MouseEvent mouseEvent) throws IOException {
        if(productsShown.contains(productIdTextField.getText())){
            productsShown.remove(productIdTextField.getText());
            finishEditingProduct();
        }
        else{
            productsShown.add(productIdTextField.getText());
            editProduct();

        }
    }


    private void editProduct() throws IOException {
        editProductController.globalManageProductsMenu = (AnchorPane) productNameAnchorPane.getParent().getParent().getParent().getParent().getParent();
        editProductController.globalProductId = productIdTextField.getText();
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().add(getIndexOfProduct() + 1, App.loadFXML("userSection/sellerSection/manageProductsMenu/edit product"));
        AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(getIndexOfProduct() + 1);
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
    private void finishEditingProduct() {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        AnchorPane headProductMenu = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text informationText = (Text) headProductMenu.getChildren().get(1);

        AnchorPane editOffAnchorPane = (AnchorPane)vBox.getChildren().get(getIndexOfProduct()+1);
        VBox editOffVBox = (VBox) editOffAnchorPane.getChildren().get(0);
        GridPane editProductGridPane = (GridPane) editOffVBox.getChildren().get(0);
        TextField editNameTextField = (TextField) editProductGridPane.getChildren().get(5);
        TextField editCompanyNameTextField = (TextField) editProductGridPane.getChildren().get(6);
        TextField editPriceTextField = (TextField) editProductGridPane.getChildren().get(7);
        TextField editSpecialFeaturesTextField = (TextField) editProductGridPane.getChildren().get(8);
        TextField editDescriptionTextField = (TextField) editProductGridPane.getChildren().get(9);
        HashMap<String, String> edits = new HashMap<>();
        String[] previousFields = getPreviousFields(productIdTextField.getText());
        if(!previousFields[0].equals(editNameTextField.getText()))
        edits.put("name", editNameTextField.getText());
        if(!previousFields[1].equals(editPriceTextField.getText()))
        edits.put("price", editPriceTextField.getText());
        if(!previousFields[2].equals(editCompanyNameTextField.getText()))
        edits.put("companyName", editCompanyNameTextField.getText());
        if(!previousFields[3].equals(editSpecialFeaturesTextField.getText()))
        edits.put("specialFeatures", editSpecialFeaturesTextField.getText());
        if(!previousFields[4].equals(editDescriptionTextField.getText()))
        edits.put("description", editDescriptionTextField.getText());
        try {
            if (edits.size() != 0) {
                System.out.println(editDescriptionTextField.getText());
                SellerController.getInstance().editProduct(productIdTextField.getText().trim(), edits);
                showMessage(informationText, MessageTypeShow.SUCCESS, "your request will be processed");
            }
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.ERROR, e.getMessage());
        }
        vBox.getChildren().remove(getIndexOfProduct()+1);

    }
    //
    private int getIndexOfProduct(){
        String productId = productIdTextField.getText();
        Product product;
        try {
            for (Product oneProduct : SellerController.getInstance().getProducts()) {
                if(oneProduct.getProductId().equals(productId))
                    product = oneProduct;
            }
        } catch (Exception e) {
            VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
            AnchorPane headProductMenu = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
            Text informationText = (Text) headProductMenu.getChildren().get(1);
            showMessage(informationText, MessageTypeShow.ERROR,e.getMessage());
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
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
    private String[] getPreviousFields(String productId){
        String[] previousFields = new String[5];
        Product product = null;
        try {
            product = SellerController.getInstance().getProduct(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder specialFeaturesString = new StringBuilder();
        HashMap<String, SpecialFeature> specialFeatures = product.getSpecialFeatures();

        for (String specialFeature : specialFeatures.keySet()) {
            specialFeaturesString.append(",").append(specialFeature).append("-").append(specialFeatures.get(specialFeature));
        }
        if (!specialFeaturesString.toString().isEmpty()) {
            specialFeaturesString = new StringBuilder(specialFeaturesString.substring(1));
        }
        previousFields[0] = product.getName();
        previousFields[1] = Integer.toString(product.getPrice());
        previousFields[2] = product.getCompanyName();
        previousFields[3] = specialFeaturesString.toString();
        previousFields[4] = product.getDescription();

        return previousFields;
    }

}
