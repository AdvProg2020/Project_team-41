package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageProductsMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

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
    private File file;

    public void addProductClicked(MouseEvent mouseEvent) {
        ArrayList<String> productDetails = new ArrayList<>();
        productDetails.add(nameTextField.getText());
        productDetails.add(amountTextField.getText());
        productDetails.add(companyNameTextField.getText());
        productDetails.add(priceTextField.getText());
        productDetails.add(categoryNameTextField.getText());
        productDetails.add(descriptionTextField.getText());
        productDetails.add(specialFeaturesTextField.getText());
        try {
            if (file != null) {

                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = fileInputStream.readAllBytes();
                List<Byte> byteList = new ArrayList<>(bytes.length);
                for (int i = 0; i < bytes.length; i++) {
                    byteList.add(i, bytes[i]);
                }
                SellerController.getInstance().addProduct(productDetails, byteList);
            }
            else {
                SellerController.getInstance().addProduct(productDetails, null);
            }
            showMessage(informationText, MessageTypeShow.SUCCESS,"sent product creation request to manager successfully");
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.ERROR,e.getMessage());
        }

    }

    public void addFileClicked(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        if (file == null) {
            showMessage(informationText, MessageTypeShow.ERROR, "no file selected");
        } else {
            showMessage(informationText, MessageTypeShow.SUCCESS, "file selected successfully");
            categoryNameTextField.setText("file");
            categoryNameTextField.setEditable(false);
            amountTextField.setText("1");
            amountTextField.setEditable(false);
        }
    }

    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message) {
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
}
