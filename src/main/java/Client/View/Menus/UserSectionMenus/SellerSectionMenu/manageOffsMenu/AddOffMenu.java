package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageOffsMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AddOffMenu {
    public AnchorPane categoryInformation;
    public TextField categoryNameTextField;
    public TextField specialFeaturesTextField;
    public Text informationText;
    public AnchorPane offInformation;
    public TextField startDateTextField;
    public TextField endDateTextField;
    public TextField amountOfDiscountTextField;
    public TextField productsTextField;

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
    private void showMessage(Text text, MessageType messageType, String message) {
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }

    public void addOffClicked(MouseEvent mouseEvent) {
    }
}
