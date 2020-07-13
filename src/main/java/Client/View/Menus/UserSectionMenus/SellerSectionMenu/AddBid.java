package Client.View.Menus.UserSectionMenus.SellerSectionMenu;

import Client.Controller.AllProductsController;
import Client.Controller.Connector;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Message.Message;
import Client.Models.Product;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.UserSectionMenus.UserSection;
import Client.View.Menus.UserSectionMenus.UserSectionMenu;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AddBid {

    public TextField productTextField;
    public TextField endDateTextField;
    public Text informationText;

    public void addBid(MouseEvent mouseEvent) throws Exception {

        Connector.getInstance().initializeMessage(new Message(new Object[]{productTextField.getText() , endDateTextField.getText() , UserSectionController.getLoggedInPerson()}));
    }

    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message) {
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);
    }



}
