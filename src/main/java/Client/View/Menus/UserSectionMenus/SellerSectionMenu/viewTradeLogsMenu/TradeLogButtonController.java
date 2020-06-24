package Client.View.Menus.UserSectionMenus.SellerSectionMenu.viewTradeLogsMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.View.Menus.MessageType;
import Client.View.Menus.NodeFinder;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TradeLogButtonController {

    public GridPane gridPane;
    public TextField logIdTextField;
    public AnchorPane tradeLogAnchorPane;
    public Button viewTradeLogButton;
    ArrayList<String> tradeLogsShown = new ArrayList<>();

    public void viewTradeLogClicked(MouseEvent mouseEvent) throws IOException {
        if(tradeLogsShown.contains(logIdTextField.getText())){
            tradeLogsShown.remove(logIdTextField.getText());
            hideTradeLog();
        }
        else{
            tradeLogsShown.add(logIdTextField.getText());
            showTradeLog();

        }
    }


    private void showTradeLog() throws IOException {
        VBox vBox = (VBox) NodeFinder.getParentById(gridPane,"tradeLogsVBox");
        vBox.getChildren().add(getIndexOfTradeLog() + 1, App.loadFXML("userSection/sellerSection/viewTradeLogs/view tradeLog"));
        AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(getIndexOfTradeLog()+1);
        VBox vBox1 = (VBox) anchorPane.getChildren().get(0);
        double anchorPaneHeight = anchorPane.getPrefHeight();
        double vBoxHeight = vBox1.getPrefHeight();
        anchorPane.setPrefHeight(0);
        anchorPane.setMaxHeight(0);
        vBox1.setPrefHeight(0);
        vBox1.setMaxHeight(0);

        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(new TimerTask() {
            int i=0;

            @Override
            public void run() {
                if (i<100) {
                    anchorPane.setPrefHeight(anchorPane.getPrefHeight()+anchorPaneHeight/100);
                    anchorPane.setMaxHeight(anchorPane.getMaxHeight()+anchorPaneHeight/100);
                    vBox1.setPrefHeight(vBox1.getPrefHeight()+vBoxHeight/100);
                    vBox1.setMaxHeight(vBox1.getMaxHeight()+vBoxHeight/100);

                } else {
                    this.cancel();
                }
                i++;
            }

        }, 0, 5);

    }
    private void hideTradeLog() throws IOException {
        VBox vBox = (VBox) NodeFinder.getParentById(gridPane,"tradeLogsVBox");
        vBox.getChildren().remove(getIndexOfTradeLog()+1);
    }
    private int getIndexOfTradeLog(){
        ArrayList<String> tradeLogDetails = null;
        try {
            for (ArrayList<String> tradeLog : SellerController.getInstance().getSalesHistory()) {
                if (tradeLog.get(0).split(":")[1].trim().equals(logIdTextField.getText().split(":")[1].trim())) {
                    tradeLogDetails = tradeLog;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ViewTradeLogController.tradeLog = tradeLogDetails;
        VBox vBox = (VBox) NodeFinder.getParentById(gridPane,"tradeLogsVBox");
        for (int i = 0; i < vBox.getChildren().size(); i++) {
            AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(i);
            VBox innerVBox = (VBox) anchorPane.getChildren().get(0);
            GridPane gridPane = (GridPane) innerVBox.getChildren().get(0);
            if (gridPane.getChildren().get(0) instanceof Text)
                continue;
            TextField textField = (TextField) NodeFinder.getChildById(gridPane, "tradeLogTextField");
            if (textField.getText().equals(logIdTextField.getText())) {
                return i;
            }
        }
        return -2;
    }
    private void showMessage(Text text,MessageType messageType, String message){
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }

}
