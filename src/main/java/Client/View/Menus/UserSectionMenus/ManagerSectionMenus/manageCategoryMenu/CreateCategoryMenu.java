package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.manageCategoryMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CreateCategoryMenu {
    public AnchorPane categoryInformation;
    public TextField categoryNameTextField;
    public TextField specialFeaturesTextField;
    public Text informationText;

    public void createCategoryClicked(MouseEvent mouseEvent) {
        String specialFeatures;
        specialFeatures = specialFeaturesTextField.getText();
        try{
            ManagerController.getInstance().addCategory(categoryNameTextField.getText(),specialFeatures);
            showMessage(informationText, MessageTypeShow.SUCCESS,"category " + categoryNameTextField.getText() + " is created");
        }
        catch (Exception e){
            showMessage(informationText, MessageTypeShow.ERROR,e.getMessage());
        }
    }
    private void showMessage(javafx.scene.text.Text text, MessageTypeShow messageTypeShow, String message) {
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
}
