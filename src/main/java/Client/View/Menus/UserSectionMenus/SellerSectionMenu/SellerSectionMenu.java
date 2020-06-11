package Client.View.Menus.UserSectionMenus.SellerSectionMenu;

import Client.Controller.UserSectionController.UserSectionController;
import Server.Database;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.example.App;

import java.io.IOException;

public class SellerSectionMenu {
    public BorderPane sellerSectionBoarderPane;
    public ScrollPane sellerSectionButtonScrollPane;

    @FXML
    public void initialize(){
        //todo remove nextLine
        UserSectionController.setLoggedInPerson(Database.getAllUsers().get(1));
    }

    public void viewPersonalInfoButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/view personal info");
    }

    public void manageProducts(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/manageProductsMenu/manage products menu");
    }

    public void removeProduct(MouseEvent mouseEvent) {
    }

    public void addProduct(MouseEvent mouseEvent) {
    }

    public void viewCompanyInfo(MouseEvent mouseEvent) {
    }

    public void viewSalesHistory(MouseEvent mouseEvent) {
    }

    public void viewBalance(MouseEvent mouseEvent) {
    }

    public void showCategories(MouseEvent mouseEvent) {
    }

    public void viewOffs(MouseEvent mouseEvent) {
    }
    private void setSubPage(String name){
        Parent root = null;
        try {
            root = App.loadFXML(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sellerSectionBoarderPane.setCenter(root);
    }

}
