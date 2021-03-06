package Client.View.Menus;

import Client.Controller.LoginRegisterController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.BackupPerson;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.Models.Person.Seller;
import Client.View.Menus.UserSectionMenus.BackupPersonMenu.BackupPersonUserSection;
import Client.View.Menus.UserSectionMenus.BuyerSectionMenus.BuyerSectionMenu;
import Client.View.Menus.UserSectionMenus.ManagerSectionMenus.ManagerSectionMenu;
import Client.View.Menus.UserSectionMenus.SellerSectionMenu.SellerSectionMenu;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.App;
import org.example.Music;
import org.example.SimpleAudioPlayer;

import java.io.IOException;

public class gMainMenu extends Menu {

    public Button loginLogout;
    public Label userSectionError;

    public void initialize() throws IOException {
        SimpleAudioPlayer.getInstance().playMusic(Music.MAIN_MENU);

        if(UserSectionController.getLoggedInPerson()==null){
            loginLogout.setText("Register/Login");
        }else{
            loginLogout.setText("Logout");
        }
        userSectionError.setVisible(false);
        try {
            if(! LoginRegisterController.getInstance().checkIfManagerExists()){
                window = new Stage();

                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Manager Register");

                scene = new Scene(loadFXML("managerRegister"));
                window.setScene(scene);
                window.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerOrLogin() throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("mainMenu");
        }else{
            logout("mainMenu");
        }
    }

    public void allProducts() throws IOException {
        App.setRoot("AllProducts");
    }

    public void userSection() throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            userSectionError.setVisible(true);
            login("mainMenu");
        }else{
            if(UserSectionController.getLoggedInPerson() instanceof Manager){
                ManagerSectionMenu.parentFxmlAddress = "mainMenu";
                App.setRoot("userSection/managerSection/manager section");
            }else if(UserSectionController.getLoggedInPerson() instanceof Seller){
                SellerSectionMenu.parentFxmlAddress = "mainMenu";
                App.setRoot("userSection/sellerSection/seller section");

            }else if(UserSectionController.getLoggedInPerson() instanceof Buyer){
                BuyerSectionMenu.parentFxmlAddress = "mainMenu";
                App.setRoot("userSection/buyerSection/buyer section");
            }else if(UserSectionController.getLoggedInPerson() instanceof BackupPerson){
                BackupPersonUserSection.parentFxmlAddress="mainMenu";
                App.setRoot("userSection/backupPersonSection/backupPersonUsersection");
            }
        }
    }

    public void offs() throws IOException {
        App.setRoot("offs");

    }


    public void aboutUs(ActionEvent actionEvent) throws IOException {
        App.setRoot("aboutUs");


    }


}
