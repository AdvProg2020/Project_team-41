package Client.View.Menus.bank;

import Client.Controller.bankController.BankAPI;
import Client.Models.bank.Receipt;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class TransferPage {
    public TextField transferPageMoneyTextField;
    public TextField transferPageCreditNumberTextField;
    public TextField transferPageDestinationTextField;
    public TextField transferPageDescriptionTextField;
    public Text transferPageInformationText;

    public void transferClicked(MouseEvent mouseEvent) {
        try {
            int id = BankAPI.getInstance().move(transferPageMoneyTextField.getText(), transferPageCreditNumberTextField.getText(), transferPageDestinationTextField.getText(), transferPageDescriptionTextField.getText());
            TransactionConfirmationPage.globalReceipt = new Receipt("transfer",Integer.parseInt(transferPageMoneyTextField.getText()),Integer.parseInt(transferPageCreditNumberTextField.getText()),Integer.parseInt(transferPageDestinationTextField.getText()),transferPageDescriptionTextField.getText(),id,0);
            BorderPane borderPane = (BorderPane) NodeFinder.getParentById(transferPageInformationText,"bankMainMenuBorderPane");
            BankMainMenu.setSubPage(borderPane,"bank/transactionConfirmationPage");
        } catch (Exception e) {
            MessageTypeShow.showMessage(transferPageInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }
}
