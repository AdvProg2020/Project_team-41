package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.viewTradeLogsMenu;

import Client.Models.Product;
import Client.Models.TradeLog;
import Server.Controller.TimeControl;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ViewTradeLogController {
    static TradeLog tradeLog;
    public TextField logIdTextField;
    public TextField dateTextField;
    public TextField deliverySituationTextField;
    public TextField discountAmountTextField;
    public TextField moneyTextField;
    public AnchorPane tradeLogInformation;
    public TextField productsTextField;
    public TextField emailTextField;
    public TextField phoneNumberTextField;
    public TextField addressTextField;

    @FXML
    public void initialize(){
        //change here
        StringBuilder products = new StringBuilder();
        for (Product product : tradeLog.getItems().keySet()) {
            products.append(",").append("name : ").append(product.getName()).append("  quantity : ").append(tradeLog.getItems().get(product));
        }
        String logId = tradeLog.getLogId();
        String date = TimeControl.getJalaliDateAndTimeForPrint(tradeLog.getDate());
        TradeLog.DeliverySituation deliverySituation = tradeLog.getDeliverySituation();
        String offAmount = tradeLog.getOffAmount()+"Rials";
        String money = tradeLog.getMoney()+"Rials";
        ArrayList<String> information = tradeLog.getInformation();

        logIdTextField.setText(logId);
        dateTextField.setText(date);
        deliverySituationTextField.setText(deliverySituation.toString());
        discountAmountTextField.setText(offAmount);
        moneyTextField.setText(money);
        emailTextField.setText(information.get(0));
        phoneNumberTextField.setText(information.get(1));
        addressTextField.setText(information.get(2));
        try {
            productsTextField.setText(products.toString().substring(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
