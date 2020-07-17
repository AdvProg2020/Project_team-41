package Client.View.Menus.Bid;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Bid;
import Client.Models.Person.Buyer;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

import static Client.View.Menus.MessageTypeShow.showMessage;
import static java.lang.Integer.parseInt;

public class OfferPrice {
    public TextField priceTextField;
    public Button priceSendingButton;
    public Text informationText;
    public static Bid bid;

    public void setPriceAndParticipate(MouseEvent mouseEvent) throws IOException {
        int initialPrice = 0;
        try {
            initialPrice = Integer.parseInt(priceTextField.getText());
            if (UserSectionController.getLoggedInPerson().getCredit() < initialPrice){
                showMessage(informationText, MessageTypeShow.ERROR, "Price can Not be lower than your credit");
            }
            else{
                bid.getBuyer_recommendedPrice().put((Buyer) UserSectionController.getLoggedInPerson(), initialPrice);
//                System.out.println(bid.getBuyer_recommendedPrice().size());
//                for (Buyer buyer : bid.getBuyer_recommendedPrice().keySet()) {
//                    System.out.println(buyer);
//                }
                App.setRoot("Bid/bidChatBox");
            }
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.ERROR, "Please enter a valid number");
        }

    }
}
