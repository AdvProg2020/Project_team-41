package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewFiles;

import Client.Controller.UserSectionController.BuyerController;
import Client.Models.Product;
import Client.View.Menus.MessageTypeShow;
import Client.View.Menus.NodeFinder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class fileSplitButtonController {
    public AnchorPane fileAnchorPane;
    public TextField productNameTextField;
    public GridPane gridPane;
    public TextField productIdTextField;
    public Button downloadFileButton;

    public void downloadFileClicked(MouseEvent mouseEvent) throws IOException {
        Text informationText;
        NodeFinder.getParentById(fileAnchorPane, "viewFilesPageAnchorPane");
        informationText = (Text) NodeFinder.getChildById((Parent) NodeFinder.getParentById(fileAnchorPane, "viewFilesPageAnchorPane"), "viewFilesInformationText");
        try {
            Product product = BuyerController.getInstance().getProduct(productIdTextField.getText());
            List<Byte> file = BuyerController.getInstance().downloadFile(product);
            writeFile(convertBytes(file));
            MessageTypeShow.showMessage(informationText, MessageTypeShow.SUCCESS, "downloaded file successfully");
        } catch (Exception e) {
            MessageTypeShow.showMessage(informationText, MessageTypeShow.ERROR, e.getMessage());
            e.printStackTrace();
        }
    }
    private byte[] convertBytes(List<Byte> Byte) {
        byte[] file = new byte[Byte.size()];
        Iterator<Byte> iterator = Byte.iterator();
        for (int i = 0; i < file.length; i++) {
            file[i] = iterator.next();
        }
        return file;
    }
    public void writeFile(byte[] file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("downloadedFiles/"+productNameTextField.getText());
        fileOutputStream.write(file);
        fileOutputStream.close();
    }
}
