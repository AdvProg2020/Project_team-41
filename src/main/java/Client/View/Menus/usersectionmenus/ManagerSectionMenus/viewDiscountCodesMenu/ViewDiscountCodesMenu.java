package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.viewDiscountCodesMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageTypeShow;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class ViewDiscountCodesMenu {
    public Text informationText;
    public VBox discountCodesVBox;
    public ScrollPane viewDiscountCodesScrollPane;
    public AnchorPane viewDiscountCodesMenuAnchorPane;

    @FXML
    public void initialize(){
        if(ManagerController.getInstance().viewAllDiscountCodes().isEmpty())
            showMessage(informationText, MessageTypeShow.INFORMATION, "No discount codes found");
        for (String discountCode : ManagerController.getInstance().viewAllDiscountCodes()) {
            makeUserSplitButton(discountCode);
        }
    }

    private void makeUserSplitButton(String username) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/managerSection/viewDiscountCodesMenu/discountCodeSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        VBox vBox = (VBox) anchorPane.getChildren().get(0);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        TextField textField = (TextField) gridPane.getChildren().get(0);
        textField.setText(username);

        discountCodesVBox.getChildren().add(root);
    }

    public void createDiscountCodeClicked(MouseEvent mouseEvent) {
        BorderPane managerSectionBoarderPane = (BorderPane)viewDiscountCodesMenuAnchorPane.getParent();
        Parent root = null;
        try {
            root = App.loadFXML("userSection/managerSection/viewDiscountCodesMenu/create discount code");
        } catch (IOException e) {
            e.printStackTrace();
        }
        managerSectionBoarderPane.setCenter(root);
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
}
