package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageOffsMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.Controller.UserSectionController.SellerController;
import Client.View.Menus.MessageType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class AddOffMenu {
    public Text informationText;
    public AnchorPane offInformation;
    public TextField startDateTextField;
    public TextField endDateTextField;
    public TextField amountOfDiscountTextField;
    public TextField productsTextField;

    public void addOffClicked(MouseEvent mouseEvent) {
        ArrayList<String> offDetails = new ArrayList<>();
        offDetails.add(startDateTextField.getText().split("-")[0]);
        offDetails.add(startDateTextField.getText().split("-")[1]);
        offDetails.add(endDateTextField.getText().split("-")[0]);
        offDetails.add(endDateTextField.getText().split("-")[1]);
        offDetails.add(amountOfDiscountTextField.getText());
        offDetails.addAll(Arrays.asList(productsTextField.getText().split(",")));
        try {
            SellerController.getInstance().addOff(offDetails);
            showMessage(informationText,MessageType.SUCCESS,"off is created successfully");
        } catch (Exception e) {
            showMessage(informationText,MessageType.ERROR,e.getMessage());
        }

    }

    private void showMessage(Text text, MessageType messageType, String message) {
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }
}
