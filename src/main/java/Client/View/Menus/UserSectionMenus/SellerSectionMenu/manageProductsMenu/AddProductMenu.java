package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageProductsMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.View.Menus.MessageType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class AddProductMenu {
    public Text informationText;
    public AnchorPane productInformation;
    public TextField nameTextField;
    public TextField amountTextField;
    public TextField companyNameTextField;
    public TextField priceTextField;
    public TextField categoryNameTextField;
    public TextField specialFeaturesTextField;
    public TextField descriptionTextField;

    public void addOffClicked(MouseEvent mouseEvent) {
        ArrayList<String> productDetails = new ArrayList<>();
        productDetails.add(nameTextField.getText());
        productDetails.add(amountTextField.getText());
        productDetails.add(companyNameTextField.getText());
        productDetails.add(priceTextField.getText());
        productDetails.add(categoryNameTextField.getText());
        productDetails.add(descriptionTextField.getText());
        productDetails.add(specialFeaturesTextField.getText());
        try {
            SellerController.getInstance().addProduct(productDetails);
            showMessage(informationText,MessageType.SUCCESS,"sent product creation request to manager successfully");
        } catch (Exception e) {
            showMessage(informationText,MessageType.ERROR,e.getMessage());
        }

    }

    private void showMessage(Text text, MessageType messageType, String message) {
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }
}
