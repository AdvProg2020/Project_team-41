package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.Bid;

import Client.Controller.BidController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static Client.View.Menus.MessageTypeShow.*;

public class EachBidWon {
    public Button payButton;
    public TextField quantity;
    public TextField bidId;
    public static Text payInfo;
    public static VBox pageVBox;

    public void payForBidProduct(MouseEvent mouseEvent) {
        try {
            BidController.payForProductInBid(bidId.getText() , Integer.parseInt(quantity.getText()));
            showMessage(payInfo , SUCCESS , "Congratulation! You bought it successfully");
            Parent root = payButton.getParent();
            pageVBox.getChildren().remove(root);
        } catch (Exception e) {
            showMessage(payInfo , ERROR , e.getMessage());
        }
    }
}
