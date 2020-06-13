package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewDiscountCodesMenu;

import Client.Controller.UserSectionController.BuyerController;
import Client.Controller.UserSectionController.SellerController;
import Client.Models.Category;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class ViewDiscountCodesMenu {
    public Text informationText;
    public AnchorPane manageDiscountCodesAnchorPane;
    public ScrollPane discountCodesScrollPane;
    public VBox discountCodesVBox;

    @FXML
    public void initialize(){
        for (String codedDiscount : BuyerController.getInstance().getCodedDiscounts()) {
            System.out.println(codedDiscount);
            makeCategorySplitButton(codedDiscount);
        }
    }

    private void makeCategorySplitButton(String categoryName) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/buyerSection/showDiscountCodesMenu/discountCodeSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        TextField textField = (TextField) anchorPane.getChildren().get(0);
        textField.setText(categoryName);

        discountCodesVBox.getChildren().add(root);
    }

}
