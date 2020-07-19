package Client.View.Menus.Bid;

import Client.Controller.BidController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Bid;
import Client.Models.Person.Buyer;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.example.App;

import static Client.View.Menus.MessageTypeShow.SUCCESS;
import static Client.View.Menus.MessageTypeShow.showMessage;

public class IncreasePrice {
    public TextField priceTextField;
    public Button priceSendingButton;
    public Text informationText;
    public static Bid bid;

    public void increasePrice(MouseEvent mouseEvent) {
        int initialPrice = 0;
        try {
            initialPrice = Integer.parseInt(priceTextField.getText());
            if (UserSectionController.getLoggedInPerson().getCredit() < initialPrice){
                showMessage(informationText, MessageTypeShow.ERROR, "Price can Not be lower than your credit");
            }
            else{
                BidController.increasePrice(bid , Integer.parseInt(priceTextField.getText()));
                showMessage(informationText , SUCCESS , "Price increased successfully");
            }
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.ERROR, "Please enter a valid number");
        }
    }
}
