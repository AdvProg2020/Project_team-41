package Client.Controller;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Buyer;
import Client.Models.Product;

public class ProductController {
    private static ProductController single_instance = null;
    public static ProductController getInstance()
    {
        if (single_instance == null)
            single_instance = new ProductController();

        return single_instance;
    }
    private ProductController(){
    }

    public static void addToCart(Product product) throws Exception {

        if(UserSectionController.getLoggedInPerson() != null){
        Buyer theBuyer = (Buyer)UserSectionController.getLoggedInPerson();
        theBuyer.getCart().getProducts().put(product,1);}
        else
            throw new Exception("First you must log in");

    }

    public static void addComment(String title , String content){
        //TODO add the comment
    }
}
