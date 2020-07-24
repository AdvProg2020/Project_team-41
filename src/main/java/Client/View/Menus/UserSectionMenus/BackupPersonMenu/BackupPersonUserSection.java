package Client.View.Menus.UserSectionMenus.BackupPersonMenu;

import Client.Controller.Connector;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Chat.ChatBox;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.View.Menus.Menu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.App;
import org.example.Music;
import org.example.SimpleAudioPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BackupPersonUserSection extends Menu {
    public ScrollPane usersScrollPane;
    public Button loginLogout;
    public AnchorPane nameAnchorPane;
    public AnchorPane sendAnchorPane;
    public ScrollPane chatScrollPane;
    public static String parentFxmlAddress;
    public VBox vbox;
    public BorderPane mainBorderPane;
    public VBox chatVBox;
    public BorderPane chatBorderPane;
    public static boolean isHeOnThisPage;
    public int numberOfChatBoxes=0;


    public void initialize() throws IOException {
        SimpleAudioPlayer.getInstance().playMusic(Music.USER_SECTION);
        isHeOnThisPage=true;
        NameOfUser.hasHeChoose=false;
        if(UserSectionController.getLoggedInPerson()==null){
            loginLogout.setText("Register/Login");
        }else{
            loginLogout.setText("Logout");
        }
        updateChatBoxes();

    }

    public void back(ActionEvent actionEvent) throws IOException {
        isHeOnThisPage=false;
        NameOfUser.currentUSer=null;
        NameOfUser.hasHeChoose=false;
        App.setRoot(parentFxmlAddress);
    }

    public void registerOrLogin(ActionEvent actionEvent) throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("mainMenu");
        }else{
            logout("mainMenu");
        }
    }
    public void updateChatBoxes() {

        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isHeOnThisPage) {
                    ArrayList<ChatBox> chatBoxes = new ArrayList<>();
                    try {
                        chatBoxes = (ArrayList<ChatBox>) Connector.getInstance().initializeMessage(new Message(new Object[]{UserSectionController.getLoggedInPerson().getUserName()},
                                MessageType.GET_ALL_CHAT_BOXES));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (chatBoxes.size() > numberOfChatBoxes) {
                        numberOfChatBoxes = chatBoxes.size();
                        putChatBoxes(chatBoxes);
                    }
                } else {
                    this.cancel();
                }
            }

        }, 0, 1000);


    }
    public void putChatBoxes(ArrayList<ChatBox> chatBoxes){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vbox.getChildren().clear();
                for (ChatBox chatBox : chatBoxes) {
                    NameOfUser.user = chatBox.getBuyer();
                    try {
                        vbox.getChildren().add(App.loadFXML("userSection/backupPersonSection/nameOfUser"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
