package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.manageCategoryMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageTypeShow;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class ManageCategoriesMenu {
    public Text informationText;
    public ScrollPane categoriesScrollPane;
    public VBox categoriesVBox;
    public AnchorPane manageCategoriesAnchorPane;

    @FXML
    public void initialize(){
        try {
            if(ManagerController.getInstance().showCategories().isEmpty()) {
                showMessage(informationText, MessageTypeShow.INFORMATION,"No categories found");
            }
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.ERROR,e.getMessage());
        }
        try {
            for (String category : ManagerController.getInstance().showCategories()) {
                makeCategorySplitButton(category);
            }
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.ERROR,e.getMessage());
        }
    }

    private void makeCategorySplitButton(String categoryName) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/managerSection/manageCategoriesMenu/categorySplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        VBox vBox = (VBox) anchorPane.getChildren().get(0);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        TextField textField = (TextField) gridPane.getChildren().get(0);
        textField.setText(categoryName);

        categoriesVBox.getChildren().add(root);
    }

    public void createCategoryClicked(MouseEvent mouseEvent) {
        BorderPane managerSectionBoarderPane = (BorderPane)manageCategoriesAnchorPane.getParent();
        Parent root = null;
        try {
            root = App.loadFXML("userSection/managerSection/manageCategoriesMenu/create category");
        } catch (IOException e) {
            e.printStackTrace();
        }
        managerSectionBoarderPane.setCenter(root);
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
}
