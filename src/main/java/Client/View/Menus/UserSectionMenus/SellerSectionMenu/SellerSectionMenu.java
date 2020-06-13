package Client.View.Menus.UserSectionMenus.SellerSectionMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Person;
import Server.Database;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.example.App;

import java.io.IOException;

public class SellerSectionMenu {
    public BorderPane sellerSectionBoarderPane;
    public ScrollPane sellerSectionButtonScrollPane;
    public TextField creditTextField;
    public AnchorPane insideAnchorPane;
    public AnchorPane outsideAnchorPane;

    @FXML
    public void initialize(){
        //todo remove nextLine
      //  UserSectionController.setLoggedInPerson(Database.getAllUsers().get(1));
    //    creditTextField.setText(SellerController.getLoggedInPerson().getCredit() +"$");
    }

    public void viewPersonalInfoButtonClicked(MouseEvent mouseEvent) {
        setSubPage("userSection/view personal info");
    }

    public void manageProducts(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/manageProductsMenu/manage products menu");
    }

    public void viewCompanyInfo(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/viewCompanyInfo/viewCompanyInfoMenu");
    }

    public void viewSalesHistory(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/viewTradeLogs/view tradeLogs menu");
    }

    public void showCategories(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/showCategoriesMenu/manage categories menu");
    }

    public void viewOffs(MouseEvent mouseEvent) {
        setSubPage("userSection/sellerSection/viewOffsMenu/manage offs menu");
    }
    private void setSubPage(String name){
        Parent root = null;
        try {
            root = App.loadFXML(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        insideAnchorPane.getChildren().setAll(root);
    }

}
