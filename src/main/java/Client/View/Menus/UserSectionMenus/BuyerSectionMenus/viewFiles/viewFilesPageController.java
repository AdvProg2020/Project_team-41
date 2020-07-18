package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewFiles;

import Client.Controller.UserSectionController.BuyerController;
import Client.Models.Product;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import Server.Controller.TimeControl;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;

public class viewFilesPageController {
    public AnchorPane viewFilesPageAnchorPane;
    public VBox filesVBox;
    public Text viewFilesInformationText;

    @FXML
    public void initialize() {
        try {
            ArrayList<Product> files = BuyerController.getInstance().getAllBoughtFiles();
            for (Product file : files) {
                makeFileSplitButton(file.getName(), file.getProductId());
            }
            if (files.isEmpty()) {
                MessageTypeShow.showMessage(viewFilesInformationText, MessageTypeShow.INFORMATION, "no file found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void makeFileSplitButton(String productName, String productId) {
        Parent root = null;
        try {
            root = App.loadFXML("userSection/buyerSection/downloadPage/fileSplitButton");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane anchorPane = (AnchorPane) root;
        TextField productNameTextField = (TextField) NodeFinder.getChildById(anchorPane,"productNameTextField");
        TextField productIdTextField = (TextField) NodeFinder.getChildById(anchorPane,"productIdTextField");
        productNameTextField.setText(productName);
        productIdTextField.setText(productId);
        filesVBox.getChildren().add(root);
    }
}
