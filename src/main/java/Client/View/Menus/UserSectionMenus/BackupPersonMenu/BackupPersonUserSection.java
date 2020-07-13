package Client.View.Menus.UserSectionMenus.BackupPersonMenu;

import Client.Controller.Connector;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Chat.ChatBox;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.View.Menus.BuyerBackup.bNameOfUser;
import Client.View.Menus.Menu;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;

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


    public void initialize() throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            loginLogout.setText("Register/Login");
        }else{
            loginLogout.setText("Logout");
        }
        ArrayList<ChatBox> chatBoxes=new ArrayList<>();
        try {
            chatBoxes= (ArrayList<ChatBox>) Connector.getInstance().initializeMessage(new Message(new Object[]{UserSectionController.getLoggedInPerson().getUserName()},
                    MessageType.GET_ALL_CHAT_BOXES));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (ChatBox chatBox : chatBoxes) {
            NameOfUser.user=chatBox.getBuyer();
            vbox.getChildren().add(App.loadFXML("userSection/backupPersonSection/nameOfUser"));
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        App.setRoot(parentFxmlAddress);
    }

    public void registerOrLogin(ActionEvent actionEvent) throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("mainMenu");
        }else{
            logout("mainMenu");
        }
    }
}
