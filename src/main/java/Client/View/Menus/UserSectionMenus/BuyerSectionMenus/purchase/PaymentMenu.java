package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.purchase;

import Client.Controller.UserSectionController.BuyerController;
import Client.View.Menus.MessageType;
import Client.View.Menus.NodeFinder;
import Client.View.Menus.UserSectionMenus.BuyerSectionMenus.BuyerSectionMenu;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class PaymentMenu {
    public Text priceText;
    public Text informationText;
    public AnchorPane paymentMenuAnchorPane;

    @FXML
    public void initialize(){
        priceText.setText(BuyerController.getInstance().calculateTotalPrice() + "Rials");
    }
    public void finishButtonClicked(MouseEvent mouseEvent) {
        try {
            BuyerController.getInstance().payForTheShop();
            showMessage(informationText,MessageType.SUCCESS,"congratulation! you bought the products");
        } catch (Exception e) {
            showMessage(informationText,MessageType.ERROR,e.getMessage());
        }
    }
    private void showMessage(Text text, MessageType messageType, String message){
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }


    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        BuyerSectionMenu.loadInInsideAnchorPane((AnchorPane) paymentMenuAnchorPane.getParent(),"userSection/buyerSection/purchase/discount code menu");
    }


}
