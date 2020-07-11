package Client.Controller;

import Client.Models.Category;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Product;
import Client.View.Menus.ProductMenu;
import Server.Controller.AllProductsServerController;

import java.util.ArrayList;

public class AllProductsController {
    private static AllProductsController single_instance = null;
    public static AllProductsController getInstance()
    {
        if (single_instance == null)
            single_instance = new AllProductsController();

        return single_instance;
    }
    private AllProductsController(){
    }
    public ArrayList<Category> getAllCategories() throws Exception {
       // return AllProductsServerController.getInstance().getAllCategories();
       return (ArrayList<Category>) Connector.getInstance().initializeMessage(new Message(null , MessageType.GET_ALL_CATEGORIES));
    }

    public void goToProductPage(String productId , ProductMenu productMenu) throws Exception {
        productMenu.setTheProduct(AllProductsController.getInstance().getProduct(productId));
        AllProductsController.getInstance().getProduct(productId).setViews
                (AllProductsController.getInstance().getProduct(productId).getViews()+1);
    }

    public Product getProduct(String productId) throws Exception {
     //  return AllProductsServerController.getInstance().getProduct(productId);
        return (Product) Connector.getInstance().initializeMessage(new Message(new Object[]{productId} , MessageType.GET_PRODUCT));
    }

}
