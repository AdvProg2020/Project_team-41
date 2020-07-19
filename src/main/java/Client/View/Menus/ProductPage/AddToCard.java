package Client.View.Menus.ProductPage;



import Client.Controller.ProductController;
import Client.View.Menus.Menu;
import Client.View.Menus.MessageTypeShow;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

import static Client.View.Menus.ProductPage.ProductPageGeneralButtons.showMessage;

public class AddToCard extends Menu {

    public Text addToCardResult;

    @FXML
    public void initialize() throws IOException {
        try{
        ProductController.addToCart(ProductPageGeneralButtons.getTheProduct());
            showMessage(addToCardResult , MessageTypeShow.SUCCESS , "The product was added to cart successfully");}
        catch(NullPointerException e){
            showMessage(addToCardResult , MessageTypeShow.ERROR , e.getMessage());
           login("ProductPage/ProductPageGeneral");
        }
        catch (Exception e){
         showMessage(addToCardResult , MessageTypeShow.ERROR , e.getMessage());
        }
    }


}
