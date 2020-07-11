package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.manageProductsMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.Models.Product;
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
import java.util.ArrayList;

public class ManageProductsMenu {

    public ScrollPane productsScrollPane;
    public VBox productsVBox;
    public Text informationText;

    @FXML
    public void initialize(){
        ArrayList<Product> allProducts = null;
        try {
            allProducts = ManagerController.getInstance().getAllProducts();
        } catch (Exception e) {
            showMessage(informationText,MessageTypeShow.ERROR,e.getMessage());
        }
        for (Product product : allProducts) {
            makeProductSplitButton(product.getName(),product.getProductId());
        }
        if (allProducts.size() == 0) {
            showMessage(informationText, MessageTypeShow.INFORMATION,"No products found");
        }
    }

    private void makeProductSplitButton(String productName,String productId) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/managerSection/manageProductsMenu/productSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        VBox vBox = (VBox) anchorPane.getChildren().get(0);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        TextField productNameTextField = (TextField) gridPane.getChildren().get(0);
        productNameTextField.setText(productName);
        TextField productIdTextField = (TextField) gridPane.getChildren().get(1);
        productIdTextField.setText(productId);
        productsVBox.getChildren().add(root);
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
}
