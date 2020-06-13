package Client.View.Menus.UserSectionMenus.ManagerSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageType;
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
            showMessage(informationText,MessageType.SUCCESS,"category " + categoryNameTextField.getText() + " is created");
            System.out.println("category " + categoryNameTextField.getText() + " is created");
        }
        catch (Exception e){
            showMessage(informationText,MessageType.ERROR,e.getMessage());
        }
    }
    private void showMessage(javafx.scene.text.Text text, MessageType messageType, String message) {
        text.setFill(messageType.getLinearGradient());
        text.setText(message);
    }
}
