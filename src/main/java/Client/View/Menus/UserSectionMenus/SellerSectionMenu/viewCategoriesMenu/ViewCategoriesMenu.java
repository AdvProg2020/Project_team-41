package Client.View.Menus.UserSectionMenus.SellerSectionMenu.viewCategoriesMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.Models.Category;
import Client.View.Menus.MessageType;
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
            showMessage(informationText, MessageType.INFORMATION, "No categories found");
        }
        for (Category category : categories)
            makeCategorySplitButton(category.getName());
    }

    private void makeCategorySplitButton(String categoryName) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/sellerSection/showCategoriesMenu/categorySplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        TextField textField = (TextField) anchorPane.getChildren().get(0);
        textField.setText(categoryName);

        categoriesVBox.getChildren().add(root);
    }
    private void showMessage(Text text, MessageType messageType, String message){
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }
}
