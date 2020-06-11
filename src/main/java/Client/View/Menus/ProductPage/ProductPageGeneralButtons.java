package Client.View.Menus.ProductPage;

import Client.Models.Comment;
import Client.Models.Product;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.example.App;

import java.io.IOException;

public class ProductPageGeneralButtons {

    private static Product theProduct;
    public BorderPane productPageBorderPane;

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

    public void backToPreviousPage(MouseEvent mouseEvent) {
       //TODO
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



}
