package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewTradeLogsMenu;

import Client.Controller.UserSectionController.BuyerController;
import Client.Models.TradeLog;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import Server.Controller.TimeControl;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ViewTradeLogsMenu {
    public Text informationText;
    public AnchorPane tradeLogsAnchorPane;
    public ScrollPane viewTradeLogsScrollPane;
    public VBox tradeLogsVBox;

    @FXML
    public void initialize(){
        try {
            ArrayList<TradeLog> tradeLogs = BuyerController.getInstance().getTradeLogs();
            for (TradeLog tradeLog : tradeLogs) {
                    makeTradeLogSplitButton(tradeLog.getLogId(),tradeLog.getDate());
            }
            if (tradeLogs.isEmpty()) {
                MessageTypeShow.showMessage(informationText, MessageTypeShow.INFORMATION, "no tradeLog found");
            }
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.INFORMATION,e.getMessage());
            e.printStackTrace();
        }
    }

    private void makeTradeLogSplitButton(String username, Date date) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/buyerSection/viewTradeLogs/tradeLogSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        TextField dateTextField = (TextField) NodeFinder.getChildById(anchorPane,"logDateTextField");
        VBox vBox = (VBox) anchorPane.getChildren().get(0);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        TextField textField = (TextField) gridPane.getChildren().get(0);
        textField.setText(username);
        dateTextField.setText(TimeControl.getJalaliDateAndTimeForPrint(date));

        tradeLogsVBox.getChildren().add(root);
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }

}
