package Client.Controller;

import Client.Models.Category;
import Client.Models.Product;
import Client.View.Menus.Menu;
import Client.View.Menus.ProductMenu;
import Server.Controller.AllProductsServerController;
import Server.Controller.LoginRegisterServerController;

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
    public ArrayList<Category> getAllCategories(){
        return AllProductsServerController.getInstance().getAllCategories();
    }

    public void goToProductPage(String productId , ProductMenu productMenu) throws Exception {
        productMenu.setTheProduct(AllProductsController.getInstance().getProduct(productId));
        AllProductsController.getInstance().getProduct(productId).setViews
                (AllProductsController.getInstance().getProduct(productId).getViews()+1);
    }

    public Product getProduct(String productId) throws Exception {
       return AllProductsServerController.getInstance().getProduct(productId);
    }

}
