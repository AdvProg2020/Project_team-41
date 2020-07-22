package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.purchase;

import Client.Controller.UserSectionController.BuyerController;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.UserSectionMenus.BuyerSectionMenus.BuyerSectionMenu;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class ReceiverInformationMenu {
    public TextField emailTextField;
    public TextField phoneNumberTextField;
    public TextField addressTextField;
    public Text informationText;
    public AnchorPane receiverInformationMenuAnchorPane;

    public void nextButtonClicked(MouseEvent mouseEvent) throws IOException {
        if(addressTextField.getText().isBlank() || phoneNumberTextField.getText().isBlank() || emailTextField.getText().isBlank())
            showMessage(informationText, MessageTypeShow.ERROR,"please enter your information");
        else {
            ArrayList<String> receiverInformation = new ArrayList<>();
            receiverInformation.add(emailTextField.getText());
            receiverInformation.add(phoneNumberTextField.getText());
            receiverInformation.add(addressTextField.getText());
            try {
                BuyerController.getInstance().setReceiverInformation(receiverInformation);
            } catch (Exception e) {
                showMessage(informationText, MessageTypeShow.ERROR, e.getMessage());
                return;
            }
            loadInInsideAnchorPane("userSection/buyerSection/purchase/discount code menu");
        }

    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }


    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        loadInInsideAnchorPane("userSection/buyerSection/viewCart/viewCart");
    }
    private void loadInInsideAnchorPane(String location){
        BuyerSectionMenu.loadInInsideAnchorPane((AnchorPane) receiverInformationMenuAnchorPane.getParent(),location);
    }
}
