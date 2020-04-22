package Server.Controller;

import Client.Models.Category;
import Client.Models.Product;

import java.util.ArrayList;

public class AllProductsServerController {
    private static AllProductsServerController single_instance = null;
    public static AllProductsServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new AllProductsServerController();

        return single_instance;
    }
    private AllProductsServerController(){

    }
    public ArrayList<Category> getAllCategories(){
        System.err.println("fail");
        return new ArrayList<>();
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
