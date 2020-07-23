package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.Bid;

import Client.Controller.BidController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Bid;
import Client.Models.Comment;
import Client.Models.CommentSituation;
import Client.View.Menus.ProductPage.ProductPageGeneralButtons;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static Client.View.Menus.MessageTypeShow.INFORMATION;
import static Client.View.Menus.MessageTypeShow.showMessage;

public class BidWinner {
    public VBox bidsVBox;
    public Text bidsInfo;


    @FXML
    public void initialize() {
        ArrayList<Bid> allBidsHeWon = new ArrayList<>();
        try {
            allBidsHeWon = BidController.getBidsHeWon(UserSectionController.getLoggedInPerson().getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (allBidsHeWon.size() == 0)
            showMessage(bidsInfo , INFORMATION , "No Bids you won yet");
        for (Bid bid : allBidsHeWon) {
            addEachBid(bid.getBidId(), bid.getProduct().getName() , bid.getWinnerInfo().getValue());
        }
        EachBidWon.payInfo = bidsInfo;
        EachBidWon.pageVBox = bidsVBox;
    }


    private void addEachBid(String bidId, String productName , int price) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/buyerSection/Bid/EachBidWon");
        } catch (IOException e) {
            e.printStackTrace();
        }

        AnchorPane anchorPane = (AnchorPane) root;
        TextField bidIdShown = (TextField) anchorPane.getChildren().get(0);
        bidIdShown.setText(bidId);
        TextField productNameShown = (TextField) anchorPane.getChildren().get(1);
        productNameShown.setText(productName);
        TextField priceShown = (TextField) anchorPane.getChildren().get(2);
        priceShown.setText(String.valueOf(price));

        bidsVBox.getChildren().add(root);
    }
}
