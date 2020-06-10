package Client.View.Menus.UserSectionMenus.ManagerSectionMenus;

import Client.Controller.UserSectionController.UserSectionController;
import Server.Database;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.example.App;

import java.io.IOException;

public class ManagerSectionMenu {
    public BorderPane managerSectionBoarderPane;
    public ScrollPane managerSectionButtonScrollPane;

    @FXML
    public void initialize(){
        //todo remove nextLine
        UserSectionController.setLoggedInPerson(Database.getAllUsers().get(0));
    }

    public void viewPersonalInfoButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/view personal info");
    }

    public void manageUsersButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/listUsersMenu/list users menu");
    }

    public void manageAllProductsButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/manageProductsMenu/manage products menu");
    }

    public void manageCategoriesButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/manageCategoriesMenu/manage categories menu");
    }

    public void manageRequestsButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/manageRequestsMenu/manage requests menu");
    }

    public void manageDiscountCodesButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/viewDiscountCodesMenu/view discount codes menu");

    }

    public void createDiscountCodeButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/managerSection/viewDiscountCodesMenu/create discount code");

    }

    private void setSubPage(String name){
        Parent root = null;
        try {
            root = App.loadFXML(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        managerSectionBoarderPane.setCenter(root);

    }
}
