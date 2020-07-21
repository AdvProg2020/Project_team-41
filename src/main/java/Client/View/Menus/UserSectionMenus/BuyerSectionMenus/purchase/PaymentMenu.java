package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.purchase;

import Client.Controller.UserSectionController.BuyerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Controller.bankController.BankAPI;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import Client.View.Menus.UserSectionMenus.BuyerSectionMenus.BuyerSectionMenu;
import Client.View.Menus.bank.BankMainMenu;
import Client.View.Menus.bank.BankWindow;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class PaymentMenu {
    public Text priceText;
    public Text informationText;
    public AnchorPane paymentMenuAnchorPane;
    public ToggleButton paymentMenuToggleButton;

    @FXML
    public void initialize(){
        priceText.setText(BuyerController.getInstance().calculateTotalPrice() + "Rials");
    }
    public void finishButtonClicked(MouseEvent mouseEvent) {
        try {
            if (!paymentMenuToggleButton.isSelected()) {
                payFromBank();
            }
            BuyerController.getInstance().payForTheShop(paymentMenuToggleButton.isSelected());
            showMessage(informationText, MessageTypeShow.SUCCESS,"congratulation! you bought the products");
            updateCredit();

        } catch (Exception e) {
            e.printStackTrace();
            showMessage(informationText, MessageTypeShow.ERROR,e.getMessage());
        }
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }


    public void backButtonClicked(MouseEvent mouseEvent) {
        BuyerSectionMenu.loadInInsideAnchorPane((AnchorPane) paymentMenuAnchorPane.getParent(),"userSection/buyerSection/purchase/discount code menu");
    }
    private void updateCredit(){

        TextField creditTextField = (TextField) NodeFinder.getChildById((Parent) NodeFinder.getParentById(paymentMenuAnchorPane, "buyerSectionBoarderPane"), "creditTextField");
        creditTextField.setText(UserSectionController.getLoggedInPerson().getCredit()+"Rials");
    }
    private int getPriceFromRawString(String rawString) {
        return Integer.parseInt(rawString.split("\\D")[0]);
    }
    private void payFromBank(){
        try {
            BankAPI.makeInstance();
            BankMainMenu.redirectedToPay = true;
            BankWindow.openBank();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
