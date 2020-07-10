package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageProductsMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.Models.Product;
import Client.Models.SpecialFeature;
import Client.View.Menus.MessageTypeShow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.HashMap;

public class editProductController {
    public static String globalProductId;
    public static AnchorPane globalManageProductsMenu;
    public String productId;
    public AnchorPane manageProductsMenu;
    public AnchorPane productInformation;
    public TextField nameTextField;
    public TextField specialFeaturesTextField;
    public TextField companyNameTextField;
    public TextField priceTextField;
    public TextField descriptionTextField;

    @FXML
    public void initialize() throws Exception {
        productId = globalProductId;
        manageProductsMenu = globalManageProductsMenu;
        StringBuilder specialFeaturesString = new StringBuilder();
        Product product = SellerController.getInstance().getProduct(productId);
        try {

            HashMap<String, SpecialFeature> specialFeatures = product.getSpecialFeatures();
            for (String categorySpecialFeature : specialFeatures.keySet()) {
                specialFeaturesString.append(",").append(categorySpecialFeature).append("-").append(specialFeatures.get(categorySpecialFeature));
            }
            if (!specialFeaturesString.toString().isEmpty()) {
                specialFeaturesString = new StringBuilder(specialFeaturesString.substring(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameTextField.setText(product.getName());
        specialFeaturesTextField.setText(specialFeaturesString.toString());
        companyNameTextField.setText(product.getCompanyName());
        priceTextField.setText(Integer.toString(product.getPrice()));
        descriptionTextField.setText(product.getDescription());
        addListener(nameTextField);
        addListener(specialFeaturesTextField);
        addListener(companyNameTextField);
        addListener(priceTextField);
        addListener(descriptionTextField);

    }
    private void addListener(TextField textField){
        Text informationText = (Text) manageProductsMenu.getChildren().get(1);
        textField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (newPropertyValue) {
                showMessage(informationText, MessageTypeShow.INFORMATION, "edits will be saved automatically");
            } else {
                informationText.setText("");
            }
        });
    }

    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message) {
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }


}
