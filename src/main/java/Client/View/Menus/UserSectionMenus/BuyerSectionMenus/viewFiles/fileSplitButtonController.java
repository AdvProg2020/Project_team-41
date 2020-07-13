package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewFiles;

import Client.Controller.UserSectionController.BuyerController;
import Client.Models.Product;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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
        try {
            Product product = BuyerController.getInstance().getProduct(productIdTextField.getText());
            List<Byte> file = BuyerController.getInstance().downloadFile(product);
            writeFile(convertBytes(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private byte[] convertBytes(List<Byte> Byte)
    {
        byte[] file = new byte[Byte.size()];
        Iterator<Byte> iterator = Byte.iterator();
        for (int i = 0; i < file.length; i++) {
            file[i] = iterator.next();
        }
        return file;
    }
    public void writeFile(byte[] file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(productNameTextField.getText());
        fileOutputStream.write(file);
        fileOutputStream.close();
    }
}
