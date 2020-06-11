package Client.View.Menus.ProductPage;

import Client.Controller.ProductController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddToCard {

    public Label addToCardResult;

    @FXML
    public void initialize(){
        try{
        ProductController.addToCart(ProductPageGeneralButtons.getTheProduct());}
        catch (ClassCastException e){
            addToCardResult.setText(e.getMessage());
        }
        catch(NullPointerException e){
            addToCardResult.setText(e.getMessage());
            //TODO go to login page automatically
        }
    }
}
