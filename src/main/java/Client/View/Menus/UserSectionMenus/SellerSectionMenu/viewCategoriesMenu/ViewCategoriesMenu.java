package Client.View.Menus.UserSectionMenus.SellerSectionMenu.viewCategoriesMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.Models.Category;
import Client.View.Menus.MessageTypeShow;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;

public class ViewCategoriesMenu {
    public Text informationText;
    public ScrollPane categoriesScrollPane;
    public VBox categoriesVBox;
    public AnchorPane manageCategoriesAnchorPane;

    @FXML
    public void initialize(){
        ArrayList<Category> categories = SellerController.getInstance().getCategories();
        if (categories.isEmpty()) {
            showMessage(informationText, MessageTypeShow.INFORMATION, "No categories found");
        }
        for (Category category : categories)
            makeCategorySplitButton(category.getName(),category.getSpecialFeatures());
    }

    private void makeCategorySplitButton(String categoryName,ArrayList<String> specialFeatures) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/sellerSection/showCategoriesMenu/categorySplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String specialFeature : specialFeatures) {
            stringBuilder.append("," + specialFeature);
        }
        String specialFeaturesString = "not available";
        try {
            specialFeaturesString = stringBuilder.toString().substring(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AnchorPane anchorPane = (AnchorPane) root;
        TextField textField = (TextField) anchorPane.getChildren().get(0);
        textField.setText(categoryName + " , special features: " + specialFeaturesString);

        categoriesVBox.getChildren().add(root);
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
}
