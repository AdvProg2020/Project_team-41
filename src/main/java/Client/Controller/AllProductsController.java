package Client.Controller;

import Client.Models.Category;
import Client.Models.Product;
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
    public ArrayList<Product> getAllProducts(){
        System.err.println("fail");
        return new ArrayList<>();

    }
    public Product showProduct(String productId){
        System.err.println("fail");
        return new Product();
    }

}
