package Client.View.Menus.bank;

import Client.Controller.UserSectionController.BuyerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Controller.bankController.BankAPI;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.View.Menus.NodeFinder;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.example.App;

import java.io.IOException;

public class BankMainMenu {
    public BorderPane bankMainMenuBorderPane;
    public AnchorPane insideAnchorPane;
    public TextField creditTextField;
    public AnchorPane outsideAnchorPane;
    public static boolean redirectedForTransfer;
    public static boolean redirectedToPay;


    public void initialize() throws Exception {
        if(redirectedForTransfer) {
            redirectedForTransfer = false;
            prepareTransferToShop(null);
        }
        if (redirectedToPay) {
            redirectedToPay = false;
            prepareTransferToShop(BuyerController.getInstance().getCart().getCashToPay());

        }
        try {
            creditTextField.setText(BankAPI.getInstance().getBalance()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void depositClicked(MouseEvent mouseEvent) {
        setSubPage("bank/depositPage");
    }

    public void withDrawClicked(MouseEvent mouseEvent) {
        setSubPage("bank/withdrawPage");
    }

    public void moveClicked(MouseEvent mouseEvent) {
        setSubPage("bank/transferPage");
    }
    public void transactionHistoryClicked(MouseEvent mouseEvent) {
        setSubPage("bank/transactionHistoryPage");
    }

    private void setSubPage(String name){
        Parent root = null;
        try {
            root = App.loadFXML(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) NodeFinder.getChildById(bankMainMenuBorderPane, "insideAnchorPane");
        anchorPane.getChildren().setAll(root);
    }
    public static void setSubPage(BorderPane borderPane,String name) {
        Parent root = null;
        try {
            root = App.loadFXML(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) NodeFinder.getChildById(borderPane, "insideAnchorPane");
        anchorPane.getChildren().setAll(root);
    }
    private void prepareTransferToShop(Integer money) throws Exception {
        moveClicked(null);
        TextField destinationTextField = (TextField) NodeFinder.getChildById(bankMainMenuBorderPane, "transferPageDestinationTextField");
        destinationTextField.setText(UserSectionController.getManagerAccountId() + "");
        destinationTextField.setEditable(false);
        if (money != null) {
            TextField moneyTextField = (TextField) NodeFinder.getChildById(bankMainMenuBorderPane, "transferPageMoneyTextField");
            moneyTextField.setText(money.toString());
            moneyTextField.setEditable(false);
        }
    }

}
