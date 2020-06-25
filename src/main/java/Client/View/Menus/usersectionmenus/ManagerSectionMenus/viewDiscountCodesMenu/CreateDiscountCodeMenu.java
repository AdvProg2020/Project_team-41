package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.viewDiscountCodesMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CreateDiscountCodeMenu {
    public Text informationText;
    public TextField discountCodeTextField;
    public TextField startDateTextField;
    public TextField endDateTextField;
    public TextField discountPercentageTextField;
    public TextField maximumDiscountTextField;
    public TextField discountRepeatsForEachUserTextField;
    public TextField peopleWhoCanUseItTextField;
    public AnchorPane discountCodeInformation;

    public void createDiscountCodeClicked(MouseEvent mouseEvent) {
        ArrayList<String> codeInformation = new ArrayList<>();
        codeInformation.add(discountCodeTextField.getText());

        try {
            if (startDateTextField.getText().split("-").length != 2) {
                throw new Exception();
            }
            codeInformation.add(startDateTextField.getText().split("-")[0]);
            codeInformation.add(startDateTextField.getText().split("-")[1]);
        } catch (Exception e) {
            showMessage(informationText,MessageType.ERROR,"wrong start date");
            return;
        }
        try {
            if (endDateTextField.getText().split("-").length != 2) {
                throw new Exception();
            }
            codeInformation.add(endDateTextField.getText().split("-")[0]);
            codeInformation.add(endDateTextField.getText().split("-")[1]);
        } catch (Exception e) {
            showMessage(informationText,MessageType.ERROR,"wrong end date");
            return;
        }
        codeInformation.add(discountPercentageTextField.getText());
        codeInformation.add(maximumDiscountTextField.getText());
        codeInformation.add(discountRepeatsForEachUserTextField.getText());
        codeInformation.add(peopleWhoCanUseItTextField.getText());

        try{
            ManagerController.getInstance().createDiscountCode(codeInformation);
            showMessage(informationText,MessageType.SUCCESS,"discount code " + discountCodeTextField.getText() + " is created");
        }
        catch (Exception e){
            e.printStackTrace();
            showMessage(informationText,MessageType.ERROR,e.getMessage());
        }
    }
    private void showMessage(Text text, MessageType messageType, String message) {
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }
}
