package Client.View.Menus;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.Models.Person.Seller;
import Client.View.Menus.UserSectionMenus.BuyerSectionMenus.BuyerSectionMenu;
import Client.View.Menus.UserSectionMenus.ManagerSectionMenus.ManagerSectionMenu;
import Client.View.Menus.UserSectionMenus.SellerSectionMenu.SellerSectionMenu;
import animatefx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.App;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AboutUs extends Menu{
    public AnchorPane aboutUsAnchorPane;
    public ImageView aboutUsImageView;
    public TextArea aboutUsTextArea;
    public Button loginLogout;

    @FXML
    public void initialize(){
        if(UserSectionController.getLoggedInPerson()==null){
            loginLogout.setText("Register/Login");
        }else{
            loginLogout.setText("Logout");
        }
        Timer animTimer = new Timer();
        aboutUsTextArea.setStyle( "-fx-background-color:#6A5ACD" );

        animTimer.scheduleAtFixedRate(new TimerTask() {
            int i=0;

            @Override
            public void run() {
            FileInputStream fileInputStream = null;
                if (i<3) {
                    try {

                        new SlideInLeft(aboutUsImageView).play();
                        fileInputStream = new FileInputStream("src/main/resources/org/example/images/aboutUsSlide/" + (i+1) + ".jpg");
                        Image image = new Image(fileInputStream);
                        aboutUsImageView.setImage(image);
                    } catch (FileNotFoundException e) {
                        System.out.println(i);
                        e.printStackTrace();
                    }
                } else {
                    i = -1;
                }
                i++;
            }

        }, 0, 2000);
    }

    public void backButtonClicked(MouseEvent mouseEvent) {
        try {
            App.setRoot("mainMenu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userSection(MouseEvent mouseEvent) throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("aboutUs");
        }else{
            if(UserSectionController.getLoggedInPerson() instanceof Manager){
                ManagerSectionMenu.parentFxmlAddress = "aboutUs";
                App.setRoot("userSection/managerSection/manager section");
            }else if(UserSectionController.getLoggedInPerson() instanceof Seller){
                SellerSectionMenu.parentFxmlAddress = "aboutUs";
                App.setRoot("userSection/sellerSection/seller section");

            }else if(UserSectionController.getLoggedInPerson() instanceof Buyer){
                BuyerSectionMenu.parentFxmlAddress = "aboutUs";
                App.setRoot("userSection/buyerSection/buyer section");


            }
        }
    }

    public void registerLogin(MouseEvent mouseEvent) throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("aboutUs");
        }else{
            logout("aboutUs");
        }
    }
}
