package Client.View.Menus.ProductPage;

import Client.Models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ProductDetails {



    public TextField nameTextField;
    public TextField companyNameTextField;
    public TextField priceTextField;
    public TextField sellerTextField;
    public TextField quantityTextField;
    public TextField specialFeaturesTextField;
    public TextField descriptionTextField;
    public TextField scoreTextField;


    @FXML
    public void initialize(){
        nameTextField.setText(ProductPageGeneralButtons.getTheProduct().getName());
        companyNameTextField.setText(ProductPageGeneralButtons.getTheProduct().getCompanyName());
        priceTextField.setText(String.valueOf(ProductPageGeneralButtons.getTheProduct().getPrice()));
        sellerTextField.setText(ProductPageGeneralButtons.getTheProduct().getSeller().getUserName());
        quantityTextField.setText(String.valueOf(ProductPageGeneralButtons.getTheProduct().getQuantity()));
        descriptionTextField.setText(ProductPageGeneralButtons.getTheProduct().getDescription());
        scoreTextField.setText(String.valueOf(ProductPageGeneralButtons.getTheProduct().calculateAverageScore()));

        StringBuilder productSpecialFeatures = new StringBuilder();
        for (String featureName : ProductPageGeneralButtons.getTheProduct().getSpecialFeatures().keySet()) {
                productSpecialFeatures.append(featureName + ":" + ProductPageGeneralButtons.getTheProduct().getSpecialFeatures().get(featureName) + "   ");
        }
        specialFeaturesTextField.setText(String.valueOf(productSpecialFeatures));
    }

}
