package Client.Controller;

import Client.Models.Category;
import Client.Models.Product;

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

    }
    public ArrayList<Product> getAllProducts(){

    }
    public Product showProduct(String productId){

    }

}