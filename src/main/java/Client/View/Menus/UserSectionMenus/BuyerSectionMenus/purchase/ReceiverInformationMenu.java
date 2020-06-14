package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.purchase;

import Client.Controller.UserSectionController.BuyerController;
import Client.View.Menus.MessageType;
import Client.View.Menus.NodeFinder;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;

public class ReceiverInformationMenu {
    public TextField emailTextField;
    public TextField phoneNumberTextField;
    public TextField addressTextField;
    public Text informationText;

    public void nextButtonClicked(MouseEvent mouseEvent) throws IOException {
        if(addressTextField.getText().isBlank() || phoneNumberTextField.getText().isBlank() || emailTextField.getText().isBlank())
            showMessage(informationText,MessageType.ERROR,"please enter your information");
        else {
            ArrayList<String> receiverInformation = new ArrayList<>();
            receiverInformation.add(addressTextField.getText());
            receiverInformation.add(phoneNumberTextField.getText());
            BuyerController.getInstance().setReceiverInformation(receiverInformation);
            App.setRoot("userSection/buyerSection/purchase/discount code menu");
        }

    }
    private void showMessage(Text text, MessageType messageType, String message){
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        BorderPane managerSectionBorderPane = (BorderPane) NodeFinder.getParentById(informationText, "managerSectionBorderPane");
        Parent root = null;
        root = App.loadFXML("userSection/buyerSection/viewCart/viewCart");
        managerSectionBorderPane.setCenter(root);
    }
}
