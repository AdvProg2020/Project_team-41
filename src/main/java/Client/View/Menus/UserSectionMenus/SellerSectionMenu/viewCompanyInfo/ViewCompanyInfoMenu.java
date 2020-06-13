package Client.View.Menus.UserSectionMenus.SellerSectionMenu.viewCompanyInfo;

import Client.Controller.UserSectionController.SellerController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ViewCompanyInfoMenu {
    public AnchorPane companyNameAnchorPane;
    public TextField companyNameTextField;

    @FXML
    public void initialize(){
        companyNameTextField.setText(SellerController.getInstance().getFactoryName());
    }
}
