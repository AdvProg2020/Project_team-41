package Client.View.Menus.ProductPage;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Product;
import Client.View.Menus.Menu;
import Client.View.Menus.MessageType;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;

public class ProductPageGeneralButtons extends Menu {

    private static Product theProduct;
    public BorderPane productPageBorderPane;
    public static String parentFxmlAddress;
    public Button loginLogout;

    public void initialize(){
        if(UserSectionController.getLoggedInPerson()==null){
            loginLogout.setText("Register/Login");
        }else{
            loginLogout.setText("Logout");
        }
    }

    public void showProductDetails(MouseEvent mouseEvent) {
        setSubPage("ProductPage/ProductDetails");
    }

    public void scoreTheProduct(MouseEvent mouseEvent) {
        setSubPage("ProductPage/ScoreProduct");
    }

    public void viewComments(MouseEvent mouseEvent) {
        setSubPage("ProductPage/ViewComments");
    }

    public void addComment(MouseEvent mouseEvent) {
        setSubPage("ProductPage/AddComment");
    }

    public void addToCard(MouseEvent mouseEvent) {
        setSubPage("ProductPage/AddToCard");
    }

    public void compareTwoProducts(MouseEvent mouseEvent) {
        setSubPage("ProductPage/CompareProducts");
    }

    public void backToPreviousPage(MouseEvent mouseEvent) throws IOException {
       App.setRoot(parentFxmlAddress);
    }

    public void registerOrLogin() throws IOException {
        if(UserSectionController.getLoggedInPerson()==null){
            login("ProductPage/ProductPageGeneral");
        }else{
            logout("ProductPage/ProductPageGeneral");
        }
    }

    private void setSubPage(String name){
        Parent root = null;
        try {
            root = App.loadFXML(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        productPageBorderPane.setCenter(root);

    }

    public static void setTheProduct(Product theProduct){ ProductPageGeneralButtons.theProduct = theProduct;
    }

    public static Product getTheProduct() {
        return theProduct;
    }

    public static void showMessage(Text text, MessageType messageType, String message) {
        text.setFill(messageType.getLinearGradient());
        text.setText(message);
    }

}
