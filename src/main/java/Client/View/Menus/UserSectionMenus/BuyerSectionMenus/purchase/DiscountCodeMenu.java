package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.purchase;

import Client.Controller.UserSectionController.BuyerController;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.UserSectionMenus.BuyerSectionMenus.BuyerSectionMenu;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
                showMessage(informationText, MessageTypeShow.ERROR, e.getMessage());
                return;
            }
        }
        else {
            try {
                BuyerController.getInstance().removeCodedDiscountFromCart();
            } catch (Exception e) {
                showMessage(informationText,MessageTypeShow.ERROR,e.getMessage());
            }
        }
        loadInInsideAnchorPane("userSection/buyerSection/purchase/payment menu");

    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        loadInInsideAnchorPane("userSection/buyerSection/purchase/receiver information menu");
    }
    private void loadInInsideAnchorPane(String location){
        BuyerSectionMenu.loadInInsideAnchorPane((AnchorPane) discountCodeMenuAnchorPane.getParent(),location);
    }
}
