package Client.View.Menus.BuyerBackup;

import Client.Controller.Connector;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Chat.ChatBox;
import Client.Models.Chat.ChatComment;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.BackupPerson;
import Client.View.Menus.NodeFinder;
import Client.View.Menus.UserSectionMenus.BackupPersonMenu.Comment;
import Client.View.Menus.UserSectionMenus.UserSection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class bNameOfUser {
    public static String user;
    public Label username;
    public AnchorPane userAnchorPane;
    public static AnchorPane fuckAnchorPane;
    public static String currentUSer;
    public static boolean hasHeChoose;

    public void initialize(){
        username.setText(user);
    }

    public void openChat(ActionEvent touchEvent) throws IOException {
        fuckAnchorPane=userAnchorPane;
        currentUSer=username.getText();
        BorderPane borderPane = (BorderPane) NodeFinder.getParentById(userAnchorPane, "mainBorderPane");
        AnchorPane nameAnchorPane = (AnchorPane) NodeFinder.getChildById(borderPane, "nameAnchorPane");
        nameAnchorPane.getChildren().clear();
        bNameBox.user = username.getText();
        nameAnchorPane.getChildren().add(App.loadFXML("buyerBackUp/nameBox"));

        AnchorPane sendAnchorPane = (AnchorPane) NodeFinder.getChildById(borderPane, "sendAnchorPane");
        sendAnchorPane.getChildren().clear();
        sendAnchorPane.getChildren().add(App.loadFXML("buyerBackUp/sendBox"));



        if(hasHeChoose==false) {
            hasHeChoose=true;
            updateComments();
        }


    }

    public static void updateComments(){
        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (currentUSer!=null) {
                    ChatBox chatBox = null;
                    try {
                        chatBox = (ChatBox) Connector.getInstance().initializeMessage(new Message(new Object[]{currentUSer,
                                UserSectionController.getLoggedInPerson().getUserName()}, MessageType.GET_CHAT_BOX));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    putComments(chatBox);

                } else {
                    this.cancel();
                }

            }

        }, 0, 1000);
    }
    public static void putComments(ChatBox chatBox){

        Platform.runLater(() -> {
            BorderPane borderPane = (BorderPane) NodeFinder.getParentById(fuckAnchorPane, "mainBorderPane");
            VBox vBox = (VBox) NodeFinder.getChildById(borderPane, "chatVBox");
            vBox.getChildren().clear();
            for (ChatComment chatComment : chatBox.getChatComments()) {
                bComment.chatComment = chatComment;
                try {
                    vBox.getChildren().add(App.loadFXML("buyerBackUp/comment"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
