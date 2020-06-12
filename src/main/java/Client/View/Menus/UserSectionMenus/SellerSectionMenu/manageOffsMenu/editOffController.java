package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageOffsMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class editOffController {
    public AnchorPane categoryInformation;
    public TextField categoryNameTextField;
    public TextField specialFeaturesTextField;
    public static String globalCategoryName;
    public static AnchorPane globalManageCategoryMenu;
    public AnchorPane offInformation;
    public TextField startDateTextField;
    public TextField endDateTextField;
    public TextField amountOfDiscountTextField;
    public TextField productsTextField;
    private String categoryName;
    private AnchorPane manageCategoryMenu;

    @FXML
    public void initialize() {
        categoryName = globalCategoryName;
        manageCategoryMenu = globalManageCategoryMenu;
        StringBuilder specialFeaturesString = new StringBuilder();
        try {
            ArrayList<String> specialFeatures = ManagerController.getInstance().getCategorySpecialFeatures(categoryName);
            for (String specialFeature : specialFeatures) {
                specialFeaturesString.append(",").append(specialFeature);
            }
            if (!specialFeaturesString.toString().isEmpty()) {
                specialFeaturesString = new StringBuilder(specialFeaturesString.substring(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        categoryNameTextField.setText(categoryName);
        specialFeaturesTextField.setText(specialFeaturesString.toString());
        Text informationText = (Text) manageCategoryMenu.getChildren().get(1);
        categoryNameTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (newPropertyValue) {
                showMessage(informationText, MessageType.INFORMATION, "edits will be saved automatically");
            }
        });
        specialFeaturesTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (newPropertyValue) {
                showMessage(informationText, MessageType.INFORMATION, "edits will be saved automatically");
            } else {
                informationText.setText("");
            }
        });

    }

    private void showMessage(Text text, MessageType messageType, String message) {
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }


}
