package Client.View.Menus.bank;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.example.App;

import java.io.IOException;

public class BankMainMenu {
    public BorderPane bankMainMenuBorderPane;

    public void depositClicked(MouseEvent mouseEvent) {
        setSubPage("bank/depositPage");
    }

    public void withDrawClicked(MouseEvent mouseEvent) {
        setSubPage("bank/withdrawPage");
    }

    public void moveClicked(MouseEvent mouseEvent) {
        setSubPage("bank/transferPage");
    }
    public void transactionHistoryClicked(MouseEvent mouseEvent) {
        setSubPage("bank/transactionHistoryPage");
    }

    private void setSubPage(String name){
        Parent root = null;
        try {
            root = App.loadFXML(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bankMainMenuBorderPane.setCenter(root);
    }
    public static void setSubPage(BorderPane borderPane,String name) {
        Parent root = null;
        try {
            root = App.loadFXML(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(root);
    }
}
