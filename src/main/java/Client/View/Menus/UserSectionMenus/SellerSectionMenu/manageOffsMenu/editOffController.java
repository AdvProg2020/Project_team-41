package Client.View.Menus.UserSectionMenus.SellerSectionMenu.manageOffsMenu;

import Client.Controller.UserSectionController.SellerController;
import Client.Models.Off;
import Client.Models.Product;
import Client.View.Menus.MessageType;
import Server.Controller.TimeControl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class editOffController {
    public static String globalOffName;
    public static AnchorPane globalViewOffsMenu;
    public AnchorPane offInformation;
    public TextField startDateTextField;
    public TextField endDateTextField;
    public TextField amountOfDiscountTextField;
    public TextField productsTextField;
    public String offName;
    public Text informationText;

    @FXML
    public void initialize() {
        AnchorPane viewOffsMenu = globalViewOffsMenu;
        offName = globalOffName;
        informationText = (Text) viewOffsMenu.getChildren().get(1);
        StringBuilder products = new StringBuilder();
        Off off = null;
        try {
            off = SellerController.getInstance().getOff(offName.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Product product : off.getProducts()) {
            products.append(",").append(product.getProductId());
        }
        startDateTextField.setText(TimeControl.getJalaliDateAndTimeForPrint(off.getStartDate()));
        endDateTextField.setText(TimeControl.getJalaliDateAndTimeForPrint(off.getEndDate()));
        amountOfDiscountTextField.setText(Integer.toString(off.getAmountOfDiscount()));
        productsTextField.setText(products.toString().substring(1));
        addListener(startDateTextField);
        addListener(endDateTextField);
        addListener(amountOfDiscountTextField);
        addListener(productsTextField);

    }

    private void addListener(TextField textField) {
        textField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (newPropertyValue) {
                showMessage(informationText, MessageType.INFORMATION, "edits will be saved automatically");
            }
        });

    }
    private void showMessage(Text text, MessageType messageType, String message) {
        text.setFill(messageType.getLinearGradient());
        text.setText(message);

    }


}
