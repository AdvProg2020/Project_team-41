package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.manageProductsMenu;

import Client.Controller.Connector;
import Client.Controller.UserSectionController.ManagerController;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Product;
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

public class ProductButtonController {
    public AnchorPane productNameAnchorPane;
    public TextField productNameTextField;
    public Button removeProductButton;
    public Button viewProductButton;
    public GridPane gridPane;
    public TextField productIdTextField;
    ArrayList<String> productsShown = new ArrayList<>();

    public void viewProductClicked(MouseEvent mouseEvent) throws Exception {
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
            e.printStackTrace();
        }
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        if(productsShown.contains(productIdTextField.getText()))
            vBox.getChildren().remove(getIndexOfProduct()+1);
        vBox.getChildren().remove(getIndexOfProduct());
        AnchorPane anchorPane = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text text = (Text) anchorPane.getChildren().get(1);
        showMessage(text, MessageTypeShow.SUCCESS,"removed the product successfully");
    }
    private void showProduct() throws Exception {
        Product product = null;
        try {
            product = ManagerController.getInstance().getProductById(productIdTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProductPageGeneralButtons.setTheProduct(product);
        App.setRoot("ProductPage/ProductPageGeneral");
        Connector.getInstance().initializeMessage(new Message(new Object[]{productIdTextField.getText()} , MessageType.INCREASE_PRODUCT_VIEW_BY_ONE));
       ProductPageGeneralButtons.parentFxmlAddress = "userSection/managerSection/manager section";
    }
    private void hideProduct() throws IOException {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().remove(getIndexOfProduct()+1);
    }
    private int getIndexOfProduct(){
        String productId = productIdTextField.getText();
        Product product;
        try {
            for (Product oneProduct : ManagerController.getInstance().getAllProducts()) {
                if(oneProduct.getProductId().equals(productId))
                    product = oneProduct;
            }
        } catch (Exception e) {
            VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
            AnchorPane anchorPane = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
            Text text = (Text) anchorPane.getChildren().get(1);
            showMessage(text, MessageTypeShow.ERROR,e.getMessage());
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
}
