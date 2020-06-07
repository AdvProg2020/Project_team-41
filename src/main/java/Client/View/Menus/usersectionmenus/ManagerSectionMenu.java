package Client.View.Menus.UserSectionMenus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        managerSectionButtonScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        managerSectionButtonScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void viewPersonalInfoButtonClicked(MouseEvent mouseEvent) throws IOException {
        setSubPage("userSection/view personal info");
    }

    public void manageUsersButtonClicked(MouseEvent mouseEvent) {
    }

    public void manageAllProductsButtonClicked(MouseEvent mouseEvent) {
    }

    public void manageCategoriesButtonClicked(MouseEvent mouseEvent) {
    }

    public void manageRequestsButtonClicked(MouseEvent mouseEvent) {
    }

    public void manageDiscountCodesButtonClicked(MouseEvent mouseEvent) {
    }

    public void createDiscountCodeButtonClicked(MouseEvent mouseEvent) {
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
