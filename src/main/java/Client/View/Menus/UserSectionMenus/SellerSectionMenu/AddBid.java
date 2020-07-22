package Client.View.Menus.UserSectionMenus.SellerSectionMenu;

import Client.Controller.AllProductsController;
import Client.Controller.Connector;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Bid;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Product;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.UserSectionMenus.UserSection;
import Client.View.Menus.UserSectionMenus.UserSectionMenu;
import Server.Database;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AddBid {

    public TextField productTextField;
    public TextField endDateTextField;
    public Text informationText;

    public void addBid(MouseEvent mouseEvent) {
        try {
            Connector.getInstance().initializeMessage(new Message(new Object[]{productTextField.getText() , endDateTextField.getText() , UserSectionController.getLoggedInPerson().getUserName()} , MessageType.ADD_BID));
            showMessage(informationText , MessageTypeShow.SUCCESS , "Bid added successfully");
        } catch (Exception e) {
            showMessage(informationText , MessageTypeShow.ERROR , e.getMessage());
        }
    }

    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message) {
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);
    }



}
