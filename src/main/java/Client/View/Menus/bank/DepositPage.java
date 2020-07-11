package Client.View.Menus.bank;

import Client.Controller.bankController.BankAPI;
import Client.Models.bank.Receipt;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class DepositPage {
    public TextField depositPageMoneyTextField;
    public TextField depositPageCreditNumberTextField;
    public TextField descriptionTextField;
    public Text depositPageInformationText;

    public void depositClicked(MouseEvent mouseEvent) {
        try {
            int id = BankAPI.getInstance().deposit(depositPageMoneyTextField.getText(), depositPageCreditNumberTextField.getText(), descriptionTextField.getText());
            TransactionConfirmationPage.globalReceipt = new Receipt("deposit",Integer.parseInt(depositPageMoneyTextField.getText()),-1,Integer.parseInt(depositPageCreditNumberTextField.getText()),descriptionTextField.getText(),id,0);
            BorderPane borderPane = (BorderPane) NodeFinder.getParentById(depositPageInformationText,"bankMainMenuBorderPane");
            BankMainMenu.setSubPage(borderPane,"bank/transactionConfirmationPage");

        } catch (Exception e) {
            MessageTypeShow.showMessage(depositPageInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }
}
