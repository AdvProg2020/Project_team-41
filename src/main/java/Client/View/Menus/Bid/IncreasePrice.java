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

import static Client.View.Menus.MessageTypeShow.*;

public class IncreasePrice {
    public TextField priceTextField;
    public Button priceSendingButton;
    public Text informationText;
    public static Bid bid;

    public void increasePrice(MouseEvent mouseEvent) {
        int newPrice = 0;
        try {
            newPrice = Integer.parseInt(priceTextField.getText());}
            catch (Exception e) {
                showMessage(informationText, MessageTypeShow.ERROR, "Please enter a valid number");
            }
            if (UserSectionController.getLoggedInPerson().getCredit() < newPrice){
                showMessage(informationText, MessageTypeShow.ERROR, "Price can Not be higher than your credit");
            }
            else{
                try {
                    BidController.increasePrice(bid , newPrice);
                    showMessage(informationText , SUCCESS , "Price increased successfully");
                } catch (Exception e) {
                    showMessage(informationText , ERROR , e.getMessage());
                }
            }
        }
    }

