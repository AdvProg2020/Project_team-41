package Server.Controller.UserSectionController;

import Client.Models.*;
import Client.Models.Person.Buyer;
import Server.Database;

public class BuyerServerController {

    private static BuyerServerController single_instance = null;
    public static BuyerServerController getInstance(){
        if (single_instance == null)
            single_instance = new BuyerServerController();

        return single_instance;
    }
    private BuyerServerController(){
    }

    public static void payForTheShop(Buyer buyer) throws Exception {
        int offAmount = 0;//todo offAmount
        Cart cart = buyer.getCart();
        for (Product product : cart.getProducts().keySet()) {
            product.decreaseQuantity();
            product.getSeller().addCredit(product.getPrice());
        }
        buyer.decreaseCredit(cart.totalPrice());

        //todo make tradelogs
        //TODO paying process
    }

    public static void rateTheProduct(String productId , Score score) throws Exception {
        Database.getProductById(productId).addScore(score);
    }
    public Product getProduct(String productId) throws Exception {
        return Database.getProductById(productId);
    }
    public static void increaseProduct(int num , String productId){
        //TODO increase number of the product
    }

    public static void decreaseProduct(int num , String productId){
        //TODO decrease number of the product
    }
    public static int calculateTotalPrice(){
        //TODO calculate the price
        int price = -1;
        return price;
    }

}
