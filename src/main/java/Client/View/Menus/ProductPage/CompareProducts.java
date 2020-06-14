package Client.View.Menus.ProductPage;

import Client.Controller.AllProductsController;
import Client.Models.Product;
import Client.View.Menus.MessageType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import static Client.View.Menus.ProductPage.ProductPageGeneralButtons.showMessage;

public class CompareProducts {
    public GridPane compareTable;
    public TextField nameTextField_1;
    public TextField descriptionTextField_1;
    public TextField priceTextField_1;
    public TextField scoreTextField_1;
    public TextField specialFeaturesTextField_1;
    public TextField nameTextField_2;
    public TextField descriptionTextField_2;
    public TextField priceTextField_2;
    public TextField scoreTextField_2;
    public TextField specialFeaturesTextField_2;
    public TextField secondId;
    public Text compareResult;

    private Product secondProduct;

    public void compareProducts(MouseEvent mouseEvent) {
        try {
            secondProduct = AllProductsController.getInstance().getProduct(secondId.getText());
        } catch (Exception e) {
            showMessage(compareResult, MessageType.ERROR, e.getMessage());
        }
        if (!ProductPageGeneralButtons.getTheProduct().getCategory().getName().equals(secondProduct.getCategory().getName())) {
            showMessage(compareResult, MessageType.ERROR, "You can only compare products of a same category!");
        }
        else{
            ProductDetails.setDetailsInTextFields(ProductPageGeneralButtons.getTheProduct() , nameTextField_1 , priceTextField_1 , descriptionTextField_1 , scoreTextField_1 , specialFeaturesTextField_1);
            ProductDetails.setDetailsInTextFields(secondProduct , nameTextField_2 , priceTextField_2 , descriptionTextField_2 , scoreTextField_2 , specialFeaturesTextField_2);
        }
    }
}
