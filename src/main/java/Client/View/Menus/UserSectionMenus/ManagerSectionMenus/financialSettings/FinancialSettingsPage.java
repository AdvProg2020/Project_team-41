package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.financialSettings;

import Client.Controller.UserSectionController.ManagerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Manager;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.UserSectionMenus.UserSection;
import Server.Controller.UserSectionController.ManagerServerController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class FinancialSettingsPage {
    public TextField minimumWage;
    public TextField minimumWalletBalance;
    public Text informationText;

    @FXML
    public void initialize() throws Exception {
        Manager manager = (Manager) UserSectionController.getLoggedInPerson();
        minimumWalletBalance.setText(Integer.toString(UserSectionController.getMinimumCredit()));
        minimumWage.setText(Integer.toString(UserSectionController.getWage()));
    }
    public void updateClicked(MouseEvent mouseEvent) {
        try {
            ManagerController.getInstance().setWage(Integer.parseInt(minimumWage.getText()));
            ManagerController.getInstance().setMinimumCredit(Integer.parseInt(minimumWalletBalance.getText()));
            MessageTypeShow.showMessage(informationText,MessageTypeShow.SUCCESS,"settings changed successfully");
        } catch (Exception e) {
            MessageTypeShow.showMessage(informationText,MessageTypeShow.ERROR,"invalid number");
        }
    }
}
