package Server.Controller.UserSectionController.BuyerAccountController;

import Client.Controller.UserSectionController.BuyerAccountController.BuyerController;
import Client.Models.*;
import Client.Models.Person.Buyer;
import Client.Models.Person.Person;
import Server.Database;

import java.util.ArrayList;

public class BuyerServerController {

    private static BuyerServerController single_instance = null;
    public static BuyerServerController getInstance(){
        if (single_instance == null)
            single_instance = new BuyerServerController();

        return single_instance;
    }
    private BuyerServerController(){
    }

    public static void payForTheShop(Buyer buyer){
        Cart cart = buyer.getCart();
        //TODO paying process
    }

    public static void rateTheProduct(String productId , Score score) throws Exception {
        Database.getProductById(productId).addScore(score);
    }
    public Product getProduct(String productId) throws Exception {
        return Database.getProductById(productId);
    }

}
