package Client.View.Menus.UserSectionMenus.SellerSectionMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DecreaseCreditPageController {
    public Text informationText;
    public TextField moneyTextField;
    public TextField accountIdTextField;

    public void submitClicked(MouseEvent mouseEvent) {
        int money = 0;
        int accountId = 0;
        try {
            money = Integer.parseInt(moneyTextField.getText());
            accountId = Integer.parseInt(accountIdTextField.getText());
        } catch (NumberFormatException e) {
            MessageTypeShow.showMessage(informationText, MessageTypeShow.ERROR, "invalid number");
            return;
        }
        try {
            SellerController.getInstance().transferMoneyToSeller(accountId,money);
            MessageTypeShow.showMessage(informationText, MessageTypeShow.SUCCESS, "transferred successfully");


        } catch (Exception e) {
            MessageTypeShow.showMessage(informationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }
}
