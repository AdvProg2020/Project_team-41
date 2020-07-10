package Client.View.Menus.UserSectionMenus.SellerSectionMenu.viewTradeLogsMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
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

public class ViewTradeLogsMenu {
    public Text informationText;
    public AnchorPane tradeLogsAnchorPane;
    public ScrollPane viewTradeLogsScrollPane;
    public VBox tradeLogsVBox;

    @FXML
    public void initialize(){
        try {
            for (ArrayList<String> tradeLogDetails : SellerController.getInstance().getSalesHistory()) {
                    makeTradeLogSplitButton(tradeLogDetails.get(0).split(":",2)[1].trim(),tradeLogDetails.get(2).split(":",2)[1].trim());
            }
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.INFORMATION,e.getMessage());
            e.printStackTrace();
        }
    }

    private void makeTradeLogSplitButton(String username, String date) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/sellerSection/viewTradeLogs/tradeLogSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        VBox vBox = (VBox) anchorPane.getChildren().get(0);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        TextField textField = (TextField) gridPane.getChildren().get(0);
        textField.setText(username);
        TextField dateTextField = (TextField) NodeFinder.getChildById(anchorPane,"logDateTextField");
        dateTextField.setText(date);

        tradeLogsVBox.getChildren().add(root);
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }

}
