package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageProductsMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.Models.Product;
import Client.View.Menus.MessageTypeShow;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    public AnchorPane manageProductsAnchorPane;

    @FXML
    public void initialize(){
        ArrayList<Product> products = SellerController.getInstance().getProducts();
        if (products.isEmpty()) {
            showMessage(informationText, MessageTypeShow.INFORMATION,"no products found");
        }
        for (Product product : products) {
            makeProductSplitButton(product.getName(),product.getProductId());
        }
    }

    private void makeProductSplitButton(String productName,String productId) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/sellerSection/manageProductsMenu/productSplitButton");
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

    public void addProductClicked(MouseEvent mouseEvent) {
        AnchorPane insideAnchorPane = (AnchorPane)manageProductsAnchorPane.getParent();
        Parent root;
        try {
            root = App.loadFXML("userSection/sellerSection/manageProductsMenu/addProduct");
            insideAnchorPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
}
