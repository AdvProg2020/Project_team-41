package Client.View.Menus;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.Models.Person.Seller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.App;

import java.io.IOException;

public class gMainMenu extends Menu {

    public Button loginLogout;
    public Label userSectionError;

    public void initialize(){
        if(UserSectionController.getLoggedInPerson()==null){
            loginLogout.setText("Register/Login");
        }else{
            loginLogout.setText("Logout");
        }
        userSectionError.setVisible(false);
    }


    public void registerOrLogin() throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("mainMenu");
        }else{
            logout("mainMenu");
        }
    }
    public void allProducts() throws IOException {
        App.setRoot("AllProducts/AllProductsGeneralButtons");
    }
    public void userSection() throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            userSectionError.setVisible(true);
        }else{
            if(UserSectionController.getLoggedInPerson() instanceof Manager){
                App.setRoot("userSection/managerSection/manager section");
            }else if(UserSectionController.getLoggedInPerson() instanceof Seller){
                App.setRoot("userSection/sellerSection/seller section");

            }else if(UserSectionController.getLoggedInPerson() instanceof Buyer){
                App.setRoot("userSection/buyerSection/buyer section");


            }
        }
    }
    public void offs() throws IOException {
        App.setRoot("offs");

    }


}
