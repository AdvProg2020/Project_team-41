package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageOffsMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.View.Menus.MessageTypeShow;
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
            showMessage(informationText, MessageTypeShow.SUCCESS,"your request will be processed");
        } catch (Exception e) {
            showMessage(informationText, MessageTypeShow.ERROR,e.getMessage());
        }

    }

    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message) {
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);
    }
}
