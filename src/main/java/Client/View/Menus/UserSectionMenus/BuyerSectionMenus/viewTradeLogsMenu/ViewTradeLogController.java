package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewTradeLogsMenu;

import Client.Models.Product;
import Client.Models.TradeLog;
import Server.Controller.TimeControl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ViewTradeLogController {
    static TradeLog tradeLog;
    public TextField logIdTextField;
    public TextField dateTextField;
    public TextField deliverySituationTextField;
    public TextField discountAmountTextField;
    public TextField moneyTextField;
    public AnchorPane tradeLogInformation;
    public TextField productsTextField;

    @FXML
    public void initialize(){
        StringBuilder products = new StringBuilder();
        for (Product product : tradeLog.getItems().keySet()) {
            products.append(",").append("name : ").append(product.getName()).append("  quantity : ").append(tradeLog.getItems().get(product));
        }
        String logId = tradeLog.getLogId();
        String date = TimeControl.getJalaliDateAndTimeForPrint(tradeLog.getDate());
        TradeLog.DeliverySituation deliverySituation = tradeLog.getDeliverySituation();
        String offAmount = tradeLog.getOffAmount()+"Rials";
        String money = tradeLog.getMoney()+"Rials";
        logIdTextField.setText(logId);
        dateTextField.setText(date);
        deliverySituationTextField.setText(deliverySituation.toString());
        discountAmountTextField.setText(offAmount);
        moneyTextField.setText(money);
        try {
            productsTextField.setText(products.toString().substring(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
