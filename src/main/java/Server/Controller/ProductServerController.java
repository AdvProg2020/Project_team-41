package Server.Controller;

import Client.Models.Comment;
import Client.Models.Off;
import Client.Models.Product;
import Client.Models.Request;
import Server.Database;

import java.util.Date;

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
        Date date=new Date();
        for (Off off : Database.getAllOffs()) {
            for (Product product : off.getProducts()) {
                if(product.getProductId().equals(productId)){
                    if(date.before(off.getStartDate())){
                        throw new Exception("There is no discount for this product at this time.");
                    }
                    return off.getAmountOfDiscount();
                }
            }
        }
        throw new Exception("There is no discount for this product at this time.");
    }
    public void addComment(Comment comment){
        Request request=new Request(comment);
        Database.addRequest(request);
    }

}