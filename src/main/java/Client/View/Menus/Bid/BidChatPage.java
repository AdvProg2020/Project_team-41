package Client.View.Menus.Bid;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Bid;
import Client.View.Menus.Menu;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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

    public void initialize(){
        isHeOnThisPage=true;
        if(UserSectionController.getLoggedInPerson()==null){
            loginLogout.setText("Register/Login");
        }else{
            loginLogout.setText("Logout");
        }
        updateChatBox();
    }

    public void back(ActionEvent actionEvent) {
        isHeOnThisPage=false;
    }

    public void registerOrLogin(ActionEvent actionEvent) throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("Bid/bidChatBox");
        }else{
            logout("Bid/bidChatBox");
        }
    }

    public void sendMessage(ActionEvent actionEvent) {
    }
    public void updateChatBox(){
        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isHeOnThisPage) {

                } else {
                    this.cancel();
                }

            }

        }, 0, 1000);


    }
}
