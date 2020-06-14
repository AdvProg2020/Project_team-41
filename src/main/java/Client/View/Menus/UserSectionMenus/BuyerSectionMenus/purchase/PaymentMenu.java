package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.purchase;

import Client.Controller.UserSectionController.BuyerController;
import Client.View.Menus.MainMenu;
import Client.View.Menus.MessageType;
import Client.View.Menus.UserSectionMenus.BuyerSection;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class PaymentMenu {
    public Text priceText;
    public Text informationText;

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
}
