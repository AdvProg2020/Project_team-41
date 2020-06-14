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

        setDetailsInTextFields(ProductPageGeneralButtons.getTheProduct() , nameTextField  , priceTextField , descriptionTextField , scoreTextField , specialFeaturesTextField);
        companyNameTextField.setText(ProductPageGeneralButtons.getTheProduct().getCompanyName());
        sellerTextField.setText(ProductPageGeneralButtons.getTheProduct().getSeller().getUserName());
        quantityTextField.setText(String.valueOf(ProductPageGeneralButtons.getTheProduct().getQuantity()));


    }

    public static void setDetailsInTextFields(Product theProduct , TextField nameTextField , TextField priceTextField , TextField descriptionTextField ,TextField scoreTextField , TextField specialFeaturesTextField){
        nameTextField.setText(theProduct.getName());
        priceTextField.setText(String.valueOf(theProduct.getPrice()));
        descriptionTextField.setText(theProduct.getDescription());
        scoreTextField.setText(String.valueOf(theProduct.calculateAverageScore()));

        StringBuilder productSpecialFeatures = new StringBuilder();
        for (String featureName : theProduct.getSpecialFeatures().keySet()) {
            productSpecialFeatures.append(featureName + ":" + theProduct.getSpecialFeatures().get(featureName) + "   ");
        }
        specialFeaturesTextField.setText(String.valueOf(productSpecialFeatures));
    }

}
