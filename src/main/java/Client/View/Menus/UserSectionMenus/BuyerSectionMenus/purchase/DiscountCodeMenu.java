package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.purchase;

import Client.Controller.UserSectionController.BuyerController;
import Client.View.Menus.MessageType;
import Client.View.Menus.NodeFinder;
import Client.View.Menus.UserSectionMenus.BuyerSectionMenus.BuyerSectionMenu;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class DiscountCodeMenu {

    public TextField discountCodeTextField;
    public Text informationText;
    public AnchorPane discountCodeMenuAnchorPane;

    public void nextButtonClicked(MouseEvent mouseEvent) throws IOException {

        if(!discountCodeTextField.getText().isBlank()) {
            try {
                BuyerController.getInstance().addCodedDiscountToCart(discountCodeTextField.getText());
            } catch (Exception e) {
                showMessage(informationText, MessageType.ERROR, e.getMessage());
                return;
            }
        }
        else {
            BuyerController.getInstance().removeCodedDiscountFromCart();
        }
        loadInInsideAnchorPane("userSection/buyerSection/purchase/payment menu");

    }
    private void showMessage(Text text, MessageType messageType, String message){
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        loadInInsideAnchorPane("userSection/buyerSection/purchase/receiver information menu");
    }
    private void loadInInsideAnchorPane(String location){
        BuyerSectionMenu.loadInInsideAnchorPane((AnchorPane) discountCodeMenuAnchorPane.getParent(),location);
    }
}
