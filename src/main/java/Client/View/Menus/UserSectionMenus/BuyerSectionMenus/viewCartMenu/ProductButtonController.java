package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewCartMenu;

import Client.Controller.UserSectionController.BuyerController;
import Client.Controller.UserSectionController.SellerController;
import Client.Models.Product;
import Client.View.Menus.MessageType;
import Client.View.Menus.NodeFinder;
import Client.View.Menus.ProductPage.ProductPageGeneralButtons;
import javafx.scene.Parent;
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

public class ProductButtonController {
    public AnchorPane productNameAnchorPane;
    public TextField productNameTextField;
    public Button viewProductButton;
    public GridPane gridPane;
    public TextField productIdTextField;
    public Button increaseProductButton;
    public Button decreaseProductButton;
    public TextField productQuantityTextField;
    public TextField singlePriceTextField;
    public TextField WholePriceTextField;
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

    private void showProduct() throws IOException {
        Product product = null;
        try {
            product = BuyerController.getInstance().getProduct(productIdTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProductPageGeneralButtons.setTheProduct(product);
        App.setRoot("ProductPage/ProductPageGeneral");
        ProductPageGeneralButtons.parentFxmlAddress = "userSection/buyerSection/buyer section";
    }

    private void hideProduct() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().remove(getIndexOfProduct()+1);
    }

    private int getIndexOfProduct(){
        String productId = productIdTextField.getText();
        Product product;
        for (Product oneProduct : SellerController.getInstance().getProducts()) {
            if(oneProduct.getProductId().equals(productId))
                product = oneProduct;
        }
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
    public void decreaseProductClicked(MouseEvent mouseEvent) {
        try {
            Product product = BuyerController.getInstance().getProduct(productIdTextField.getText());
            BuyerController.getInstance().getCart().decreaseProductQuantity(product);
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void increaseProductClicked(MouseEvent mouseEvent) {
        try {
            Product product = BuyerController.getInstance().getProduct(productIdTextField.getText());
            BuyerController.getInstance().getCart().increaseProductQuantity(product);
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMessage(Text text,MessageType messageType, String message){
        text.setFill(messageType.getLinearGradient());
        text.setText(message);
    }

    private void resetPage(){
        AnchorPane insideAnchorPane = (AnchorPane) NodeFinder.getParentById(productNameAnchorPane,"insideAnchorPane");
        Parent root = null;
        try {
            root = App.loadFXML("userSection/buyerSection/viewCart/viewCart");
        } catch (IOException e) {
            e.printStackTrace();
        }
        insideAnchorPane.getChildren().setAll(root);
    }

}
