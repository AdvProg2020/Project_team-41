package Client.View.Menus.UserSectionMenus.SellerSectionMenu.viewTradeLogsMenu;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ViewTradeLogController {
    static ArrayList<String> tradeLog;
    public TextField logIdTextField;
    public TextField buyerTextField;
    public TextField dateTextField;
    public TextField deliverySituationTextField;
    public TextField offAmountTextField;
    public TextField moneyTextField;
    public AnchorPane tradeLogInformation;
    @FXML
    public void initialize(){
        String logId = tradeLog.get(0).split(":")[1].trim();
        String buyer = tradeLog.get(1).split(":")[1].trim();
        String date = tradeLog.get(2).split(":")[1].trim();
        String deliverySituation = tradeLog.get(3).split(":")[1].trim();
        String offAmount = tradeLog.get(4).split(":")[1].trim();
        String money = tradeLog.get(5).split(":")[1].trim();
        logIdTextField.setText(logId);
        buyerTextField.setText(buyer);
        dateTextField.setText(date);
        deliverySituationTextField.setText(deliverySituation);
        offAmountTextField.setText(offAmount);
        moneyTextField.setText(money);

    }


}
