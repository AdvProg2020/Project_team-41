package Client.View.Menus.Bid;

import Client.Controller.Connector;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Bid;
import Client.Models.BidChat.BidChatBox;
import Client.Models.BidChat.BidChatComment;
import Client.Models.Chat.ChatBox;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.Buyer;
import Client.View.Menus.Menu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.App;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class BidChatPage extends Menu {
    public static Bid bid;
    public BorderPane mainBorderPane;
    public Button loginLogout;
    public BorderPane chatBorderPane;
    public AnchorPane sendAnchorPane;
    public ScrollPane chatScrollPane;
    public VBox chatVBox;
    public TextField productId;
    public TextField productName;
    public TextField highestBid;
    public TextField message;
    public boolean isHeOnThisPage;
    public int numberOfComments;

    public void initialize() throws Exception {
        isHeOnThisPage = true;
        numberOfComments = 0;
        if (UserSectionController.getLoggedInPerson() == null) {
            loginLogout.setText("Register/Login");
        } else {
            loginLogout.setText("Logout");
        }
        productId.setText(bid.getProduct().getProductId());
        productName.setText(bid.getProduct().getName());

        updateChatBox();
    }

    public void back(ActionEvent actionEvent) throws IOException {
        isHeOnThisPage = false;
        App.setRoot("UserSection/buyerSection/buyer section");
    }

    public void registerOrLogin(ActionEvent actionEvent) throws IOException {
        if (UserSectionController.getLoggedInPerson() == null) {
            login("Bid/bidChatBox");
        } else {
            logout("Bid/bidChatBox");
        }
    }

    public void sendMessage(ActionEvent actionEvent) {
        BidChatComment bidChatComment = new BidChatComment(UserSectionController.getLoggedInPerson().getUserName(),
                message.getText(), bid.getBidId());
        BidComment.bidChatComment = bidChatComment;
        try {
            chatVBox.getChildren().add(App.loadFXML("Bid/bidComment"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Connector.getInstance().initializeMessage(new Message(new Object[]{bidChatComment}, MessageType.ADD_BID_COMMENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        message.clear();


    }

    public void updateChatBox() {
        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isHeOnThisPage) {
                    try {
                        Bid.getAllBids();

                        Bid shownBid = (Bid) Connector.getInstance().initializeMessage(new Message(new Object[]{bid.getBidId()}, MessageType.GET_BID_BY_ID));
                        highestBid.setText(String.valueOf(shownBid.getWinnerInfo().getValue()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    BidChatBox bidChatBox = null;
                    try {
                        bidChatBox = (BidChatBox) Connector.getInstance().initializeMessage(new Message(new Object[]{bid.getBidId()},
                                MessageType.GET_BID_CHAT_BOX));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (bidChatBox.getChatComments().size() > numberOfComments) {
                        numberOfComments = bidChatBox.getChatComments().size();
                        putComments(bidChatBox);
                    }


                } else {
                    this.cancel();
                }

            }

        }, 0, 3000);


    }

    public void increasePrice(ActionEvent actionEvent) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Increase Price");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Bid/IncreasePrice.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
        IncreasePrice.bid = bid;
        window.showAndWait();
    }

    public void putComments(BidChatBox bidChatBox) {
        Platform.runLater(() -> {
            chatVBox.getChildren().clear();
            for (BidChatComment chatComment : bidChatBox.getChatComments()) {
                BidComment.bidChatComment = chatComment;
                try {
                    chatVBox.getChildren().add(App.loadFXML("Bid/bidComment"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
    }
}

