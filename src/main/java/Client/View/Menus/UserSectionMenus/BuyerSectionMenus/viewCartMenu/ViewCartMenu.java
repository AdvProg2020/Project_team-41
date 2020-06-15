package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewCartMenu;

import Client.Controller.UserSectionController.BuyerController;
import Client.Models.Cart;
import Client.Models.Product;
import Client.View.Menus.MessageType;
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
            System.out.println("cart is empty");
        }
        else {
            for (Product product : cart.getProducts().keySet()) {
                makeProductSplitButton(product.getName(), product.getProductId(), cart.getProducts().get(product));
            }
            totalPriceText.setText(Integer.toString(cart.totalPrice()));
        }
    }

    private void makeProductSplitButton(String productName,String productId,int quantity) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/buyerSection/viewCart/productSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        TextField productNameTextField = (TextField) anchorPane.getChildren().get(0);
        TextField productIdTextField = (TextField) anchorPane.getChildren().get(1);
        TextField quantityTextField = (TextField) anchorPane.getChildren().get(2);
        productNameTextField.setText(productName);
        productIdTextField.setText(productId);
        quantityTextField.setText(Integer.toString(quantity));

        productsVBox.getChildren().add(root);
    }

    public void purchaseButtonClicked(MouseEvent mouseEvent) throws IOException {
        if (BuyerController.getInstance().getCart().getProducts().isEmpty()) {
            showMessage(informationText,MessageType.ERROR,"you haven't selected any products yet");
        }
        else {
            App.setRoot("userSection/buyerSection/purchase/receiver information menu");
        }
    }
    private void showMessage(Text text, MessageType messageType, String message){
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }
}
