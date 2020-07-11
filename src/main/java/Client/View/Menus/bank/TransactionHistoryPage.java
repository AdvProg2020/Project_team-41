package Client.View.Menus.bank;

import Client.Controller.bankController.BankAPI;
import Client.Models.bank.Receipt;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TransactionHistoryPage {
    public Text transactionHistoryPageInformationText;
    public VBox transactionHistoryPageVBox;

    public void incrementClicked(MouseEvent mouseEvent) {
        try {
            transactionHistoryPageVBox.getChildren().removeAll(transactionHistoryPageVBox.getChildrenUnmodifiable());
            for (Receipt receipt : BankAPI.getInstance().getReceipts(BankAPI.TransactionType.INCREMENT)) {
                TextField textField = new TextField();
                textField.setPrefWidth(760.0);
                textField.setText(receipt.toString());
                transactionHistoryPageVBox.getChildren().add(textField);
            }
        } catch (Exception e) {
            MessageTypeShow.showMessage(transactionHistoryPageInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }

    public void allHistoryClicked(MouseEvent mouseEvent) {
        try {
            transactionHistoryPageVBox.getChildren().removeAll(transactionHistoryPageVBox.getChildrenUnmodifiable());
            for (Receipt receipt : BankAPI.getInstance().getReceipts(BankAPI.TransactionType.ALL_HISTORY)) {
                TextField textField = new TextField();
                textField.setPrefWidth(760.0);
                textField.setText(receipt.toString());
                transactionHistoryPageVBox.getChildren().add(textField);
            }
        } catch (Exception e) {
            MessageTypeShow.showMessage(transactionHistoryPageInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }

    public void decrementClicked(MouseEvent mouseEvent) {
        try {
            transactionHistoryPageVBox.getChildren().removeAll(transactionHistoryPageVBox.getChildrenUnmodifiable());
            for (Receipt receipt : BankAPI.getInstance().getReceipts(BankAPI.TransactionType.DECREMENT)) {
                TextField textField = new TextField();
                textField.setPrefWidth(760.0);
                textField.setText(receipt.toString());
                transactionHistoryPageVBox.getChildren().add(textField);
            }
        } catch (Exception e) {
            MessageTypeShow.showMessage(transactionHistoryPageInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }
}
