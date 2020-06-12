package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageOffsMenu;

import Client.Controller.UserSectionController.ManagerController;
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

public class ManageOffsMenu {
    public Text informationText;
    public ScrollPane categoriesScrollPane;
    public VBox categoriesVBox;
    public AnchorPane manageCategoriesAnchorPane;
    public AnchorPane manageOffsAnchorPane;
    public VBox offsVBox;
    public ScrollPane offsScrollPane;

    @FXML
    public void initialize(){
        if(ManagerController.getInstance().showCategories().isEmpty())
            System.out.println("there is no category to show here");
        for (String category : ManagerController.getInstance().showCategories()) {
            makeCategorySplitButton(category);
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

    public void addOffClicked(MouseEvent mouseEvent) {
    }
}
