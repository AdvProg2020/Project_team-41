package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewCartMenu;

import Client.Controller.UserSectionController.BuyerController;
import Client.Models.Cart;
import Client.Models.Product;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class ViewCartMenu {
    public Text informationText;
    public AnchorPane viewCartAnchorPane;
    public ScrollPane productsScrollPane;
    public VBox productsVBox;
    public Text totalPriceText;

    @FXML
    public void initialize(){
        Cart cart = BuyerController.getInstance().getCart();
        if(cart.getProducts().isEmpty()){
            showMessage(informationText, MessageTypeShow.INFORMATION,"cart is empty");
        }
        else {
            for (Product product : cart.getProducts().keySet()) {
                makeProductSplitButton(product.getName(), product.getProductId(), cart.getProducts().get(product),product.getPriceWithOff());
            }
            totalPriceText.setText(Integer.toString(cart.totalPrice()));
        }
    }

    private void makeProductSplitButton(String productName, String productId, Integer quantity, int price) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/buyerSection/viewCart/productSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        TextField productNameTextField = (TextField) NodeFinder.getChildById(anchorPane,"productNameTextField");
        TextField productIdTextField = (TextField) NodeFinder.getChildById(anchorPane,"productIdTextField");
        TextField quantityTextField = (TextField) NodeFinder.getChildById(anchorPane,"productQuantityTextField");
        TextField singlePriceTextField = (TextField) NodeFinder.getChildById(anchorPane,"singlePriceTextField");
        TextField wholePriceTextField = (TextField) NodeFinder.getChildById(anchorPane,"WholePriceTextField");
        productNameTextField.setText(productName);
        productIdTextField.setText(productId);
        quantityTextField.setText(quantity.toString());
        singlePriceTextField.setText("each : "+price+" Rials");
        wholePriceTextField.setText("all : "+price*quantity+" Rials");
        productsVBox.getChildren().add(root);
    }

    public void purchaseButtonClicked(MouseEvent mouseEvent) throws IOException {
        if (BuyerController.getInstance().getCart().getProducts().isEmpty()) {
            showMessage(informationText, MessageTypeShow.ERROR,"you haven't selected any products yet");
        }
        else {
            loadInInsideAnchorPane();
        }
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
    private void loadInInsideAnchorPane(){
        AnchorPane insideAnchorPane = (AnchorPane) viewCartAnchorPane.getParent();
        Parent root = null;
        try {
            root = App.loadFXML("userSection/buyerSection/purchase/receiver information menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
        insideAnchorPane.getChildren().setAll(root);
    }
}
