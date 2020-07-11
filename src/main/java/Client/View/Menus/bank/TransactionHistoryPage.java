package Client.View.Menus.bank;

import Client.Controller.bankController.BankAPI;
import Client.Models.bank.Receipt;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TransactionHistoryPage {
    public Text transactionHistoryPageInformationText;
    public VBox transactionHistoryPageVBox;

    public void incrementClicked(MouseEvent mouseEvent) {
        try {
            for (Receipt receipt : BankAPI.getInstance().getReceipts(BankAPI.TransactionType.INCREMENT)) {
                TextArea textArea = new TextArea();
                textArea.setText(receipt.toString());
                transactionHistoryPageVBox.getChildren().add(textArea);
            }
        } catch (Exception e) {
            MessageTypeShow.showMessage(transactionHistoryPageInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }

    public void allHistoryClicked(MouseEvent mouseEvent) {
        try {
            for (Receipt receipt : BankAPI.getInstance().getReceipts(BankAPI.TransactionType.ALL_HISTORY)) {
                TextArea textArea = new TextArea();
                textArea.setText(receipt.toString());
                transactionHistoryPageVBox.getChildren().add(textArea);
            }
        } catch (Exception e) {
            MessageTypeShow.showMessage(transactionHistoryPageInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }

    public void decrementClicked(MouseEvent mouseEvent) {
        try {
            for (Receipt receipt : BankAPI.getInstance().getReceipts(BankAPI.TransactionType.DECREMENT)) {
                TextArea textArea = new TextArea();
                textArea.setText(receipt.toString());
                transactionHistoryPageVBox.getChildren().add(textArea);
            }
        } catch (Exception e) {
            MessageTypeShow.showMessage(transactionHistoryPageInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }
}
