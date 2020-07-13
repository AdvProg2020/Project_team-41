package Client.View.Menus.BuyerBackup;

import Client.Controller.Connector;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.BackupPerson;
import Client.View.Menus.Menu;
import Client.View.Menus.UserSectionMenus.BackupPersonMenu.NameOfUser;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;

public class BuyerBackup extends Menu {
    public ScrollPane usersScrollPane;
    public Button loginLogout;
    public AnchorPane nameAnchorPane;
    public AnchorPane sendAnchorPane;
    public ScrollPane chatScrollPane;
    public VBox vbox;
    public VBox chatVBox;
    public BorderPane mainBorderPane;
    public BorderPane chatBorderPane;


    public void initialize() throws IOException {
        bNameOfUser.hasHeChoose=false;
        if(UserSectionController.getLoggedInPerson()==null){
            loginLogout.setText("Register/Login");
        }else{
            loginLogout.setText("Logout");
        }
        ArrayList<BackupPerson> backupPeople = new ArrayList<>();
        try {
            backupPeople= (ArrayList<BackupPerson>)  Connector.getInstance().initializeMessage(new Message(null, MessageType.GET_BACKUPS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!backupPeople.isEmpty()){
            for (BackupPerson backupPerson : backupPeople) {
                bNameOfUser.user=backupPerson.getUserName();
                vbox.getChildren().add(App.loadFXML("buyerBackUp/nameOfUser"));

            }
        }

    }

    public void back(ActionEvent actionEvent) throws IOException {
        bNameOfUser.currentUSer=null;
        bNameOfUser.hasHeChoose=false;
        App.setRoot("userSection/buyerSection/buyer section");
    }

    public void registerOrLogin(ActionEvent actionEvent) throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("mainMenu");
        }else{
            logout("mainMenu");
        }
    }
}
