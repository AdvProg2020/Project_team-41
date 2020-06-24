package Client.View.Menus.ProductPage;



import Client.Controller.ProductController;
import Client.View.Menus.Menu;
import Client.View.Menus.MessageType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

import static Client.View.Menus.ProductPage.ProductPageGeneralButtons.showMessage;

public class AddToCard extends Menu {

    public Text addToCardResult;

    @FXML
    public void initialize() throws IOException {
        try{
        ProductController.addToCart(ProductPageGeneralButtons.getTheProduct());
            showMessage(addToCardResult , MessageType.SUCCESS , "The product was added to cart successfully");}
        catch (ClassCastException e){
         showMessage(addToCardResult , MessageType.ERROR , e.getMessage());
        }
        catch(NullPointerException e){
            showMessage(addToCardResult , MessageType.ERROR , e.getMessage());
           login("ProductPage/ProductPageGeneral");
        }
    }


}
