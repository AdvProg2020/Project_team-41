package Client.View.Menus.bank;

import Client.Controller.bankController.BankAPI;
import Client.Models.bank.Receipt;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class TransactionConfirmationPage {
    public TextArea confirmationPageTextArea;
    public Text confirmationPageInformationText;
    public static Receipt globalReceipt;
    private Receipt receipt;
    @FXML
    public void initialize(){
        receipt = globalReceipt;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("type: ").append(receipt.getReceiptType()).append("\n");
        stringBuilder.append("id: ").append(receipt.getId()).append("\n");
        if(receipt.getSourceAccountID() != -1)
            stringBuilder.append("source account id: ").append(receipt.getSourceAccountID()).append("\n");
        if(receipt.getDestAccountID() != -1)
            stringBuilder.append("destination account id: ").append(receipt.getDestAccountID()).append("\n");
        stringBuilder.append("money: ").append(receipt.getMoney()).append("\n");
        stringBuilder.append("description: ").append(receipt.getDescription()).append("\n");
        confirmationPageTextArea.setText(stringBuilder.toString());
    }
    public void backClicked(MouseEvent mouseEvent) {
        BorderPane borderPane = (BorderPane) NodeFinder.getParentById(confirmationPageInformationText,"bankMainMenuBorderPane");
        switch (receipt.getReceiptType()){
            case ("withdraw"):{
                BankMainMenu.setSubPage(borderPane,"bank/withdrawPage");
                break;
            }
            case ("transfer"):{
                BankMainMenu.setSubPage(borderPane,"bank/transferPage");
                break;
            }
            case ("deposit"):{
                BankMainMenu.setSubPage(borderPane,"bank/depositPage");
                break;
            }

        }
    }

    public void submitClicked(MouseEvent mouseEvent) {
        try {
            BankAPI.getInstance().pay(Integer.toString(receipt.getId()));
            MessageTypeShow.showMessage(confirmationPageInformationText,MessageTypeShow.SUCCESS,"operation done successfully");
            TextField creditTextField = (TextField) NodeFinder.getChildById((Parent) NodeFinder.getParentById(confirmationPageInformationText, "bankMainMenuBorderPane"), "creditTextField");
            creditTextField.setText(BankAPI.getInstance().getBalance()+"");
        } catch (Exception e) {
            MessageTypeShow.showMessage(confirmationPageInformationText,MessageTypeShow.ERROR,e.getMessage());
        }
    }
}
