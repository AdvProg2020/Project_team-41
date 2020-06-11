package Client.View.Menus.ProductPage;

import Client.Controller.ProductController;
import Client.View.Menus.MessageType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddToCard {

    public Text addToCardResult;

    @FXML
    public void initialize(){
        try{
        ProductController.addToCart(ProductPageGeneralButtons.getTheProduct());
            showMessage(addToCardResult , MessageType.SUCCESS , "The product was added to card successfully");}
        catch (ClassCastException e){
          showMessage(addToCardResult , MessageType.ERROR , e.getMessage());
        }
        catch(NullPointerException e){
            showMessage(addToCardResult , MessageType.ERROR , e.getMessage());
            //TODO go to login page automatically
        }
    }

    private void showMessage(Text text, MessageType messageType, String message) {
        text.setFill(messageType.getLinearGradient());
        text.setText(message);
    }
}
