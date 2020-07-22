package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.Bid;

import Client.Controller.BidController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import static Client.View.Menus.MessageTypeShow.*;

public class EachBidWon {
    public Button payButton;
    public TextField quantity;
    public Text payInfo;
    public TextField bidId;

    public void payForBidProduct(MouseEvent mouseEvent) {
        try {
            BidController.payForProductInBid(bidId.getText() , Integer.parseInt(quantity.getText()));
            showMessage(payInfo , SUCCESS , "Congratulation! You bought it successfully");
        } catch (Exception e) {
            showMessage(payInfo , ERROR , e.getMessage());
        }
    }
}
