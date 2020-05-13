package Server.Controller;

import Client.Models.Off;
import Client.Models.Product;
import Server.Database;

public class ProductServerController {
    private static ProductServerController single_instance = null;
    public static ProductServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new ProductServerController();

        return single_instance;
    }
    private ProductServerController(){
    }

    public static void addToCart(String Id){

    }

    public static void addComment(String title , String content){
        //TODO add the comment
    }
    public int amountOfDiscount(String productId) throws Exception {
        for (Off off : Database.getAllOffs()) {
            for (Product product : off.getProducts()) {
                if(product.getProductId().equals(productId)){
                    return off.getAmountOfDiscount();
                }
            }
        }
        throw new Exception("There is no discount for this product at this time.");
    }

}