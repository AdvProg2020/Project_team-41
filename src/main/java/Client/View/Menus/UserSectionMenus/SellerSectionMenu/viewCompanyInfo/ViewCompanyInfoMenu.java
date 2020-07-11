package Client.View.Menus.UserSectionMenus.SellerSectionMenu.viewCompanyInfo;

import Client.Controller.UserSectionController.SellerController;
import Client.View.Menus.MessageTypeShow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ViewCompanyInfoMenu {
    public AnchorPane companyNameAnchorPane;
    public TextField companyNameTextField;

    public void initialize(){
        try {
            companyNameTextField.setText(SellerController.getInstance().getFactoryName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
}
