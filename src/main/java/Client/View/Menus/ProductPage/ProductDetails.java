package Client.View.Menus.ProductPage;

import Client.Models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ProductDetails {

    private Product theProduct;

    public TextField nameTextField;
    public TextField companyNameTextField;
    public TextField priceTextField;
    public TextField sellerTextField;
    public TextField quantityTextField;
    public TextField specialFeaturesTextField;

    @FXML
    public void initialize(){
        nameTextField.setText(theProduct.getName());
        companyNameTextField.setText(theProduct.getCompanyName());
        priceTextField.setText(String.valueOf(theProduct.getPrice()));
        sellerTextField.setText(theProduct.getSeller().getUserName());
        quantityTextField.setText(String.valueOf(theProduct.getQuantity()));

        StringBuilder productSpecialFeatures = new StringBuilder();
        for (String featureName : theProduct.getSpecialFeatures().keySet()) {
            productSpecialFeatures.append(featureName + theProduct.getSpecialFeatures().get(featureName) + "\n");
        }
        specialFeaturesTextField.setText(String.valueOf(productSpecialFeatures));
    }
}
