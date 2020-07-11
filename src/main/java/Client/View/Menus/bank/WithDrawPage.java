package Client.View.Menus.bank;

import Client.Controller.bankController.BankAPI;
import Client.Models.bank.Receipt;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class WithDrawPage {
    public TextField withDrawMoneyTextField;
    public TextField creditNumberTextField;
    public TextField descriptionTextField;
    public Text withDrawInformationText;

    public void withDrawClicked(MouseEvent mouseEvent) {
        try {
            int id = BankAPI.getInstance().withDraw(withDrawMoneyTextField.getText(), creditNumberTextField.getText(), descriptionTextField.getText());
            TransactionConfirmationPage.globalReceipt = new Receipt("withdraw",Integer.parseInt(withDrawMoneyTextField.getText()),Integer.parseInt(creditNumberTextField.getText()),-1,descriptionTextField.getText(),id,0);
            BorderPane borderPane = (BorderPane) NodeFinder.getParentById(withDrawInformationText,"bankMainMenuBorderPane");
            BankMainMenu.setSubPage(borderPane,"bank/transactionConfirmationPage");
        } catch (Exception e) {
            MessageTypeShow.showMessage(withDrawInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }
}
