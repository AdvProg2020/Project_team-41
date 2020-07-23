package Server.Controller;

import Client.Models.Bid;
import Client.Models.Category;
import Client.Models.Product;
import Server.Database;

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
        return Database.getInstance().getAllCategory();
    }
    public ArrayList<Product> getAllProductsForFilter() throws Exception {
        ArrayList<Product> allProducts = new ArrayList<>(Database.getInstance().getAllProducts());
        ArrayList<Product> bidProducts = new ArrayList<>();
        for (Bid bid : Database.getInstance().getAllBids()) {
            bidProducts.add(bid.getProduct());
        }
         allProducts.removeAll(bidProducts);
        return allProducts;
    }
    public Product getProduct(String productId) throws Exception {
        return Database.getInstance().getProductById(productId);
    }

}
