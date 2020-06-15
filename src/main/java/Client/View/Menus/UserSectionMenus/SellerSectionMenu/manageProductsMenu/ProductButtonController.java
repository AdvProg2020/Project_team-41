package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageProductsMenu;

import Client.Controller.UserSectionController.SellerController;
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
            SellerController.getInstance().removeProduct(productIdTextField.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        if(productsShown.contains(productIdTextField.getText()))
            vBox.getChildren().remove(getIndexOfProduct()+1);
        vBox.getChildren().remove(getIndexOfProduct());
        AnchorPane anchorPane = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text text = (Text) anchorPane.getChildren().get(1);
        showMessage(text, MessageType.SUCCESS,"your request will be processed");


    }
    private void showProduct() throws IOException {
        Product product = null;
        try {
            product = SellerController.getInstance().getProduct(productIdTextField.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ProductPageGeneralButtons.setTheProduct(product);
        App.setRoot("ProductPage/ProductPageGeneral");
        ProductPageGeneralButtons.parentFxmlAddress = "userSection/sellerSection/seller section";
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
