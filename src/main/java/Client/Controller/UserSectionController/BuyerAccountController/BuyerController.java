package Client.Controller.UserSectionController.BuyerAccountController;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Cart;
import Client.Models.CodedDiscount;
import Client.Models.Person.Buyer;
import Client.Models.Product;
import Client.Models.TradeLog;
import Server.Controller.UserSectionController.BuyerAccountController.BuyerServerController;

import java.util.ArrayList;
import java.util.Dictionary;

public class BuyerController extends UserSectionController {

    private static BuyerController single_instance = null;
    public static BuyerController getInstance(){
        if (single_instance == null)
            single_instance = new BuyerController();

        return single_instance;
    }
    private BuyerController(){

    }
    public int getBalance(){
        return loggedInPerson.getCredit();
    }
    public ArrayList<CodedDiscount> getCodedDiscounts(){
        return BuyerServerController.getCodedDiscounts();
    }

    public boolean payForTheShop(){
        return BuyerServerController.payForTheShop();
    }

    public ArrayList<TradeLog> getTradeLogs(){
        return loggedInPerson.getTradeLogs();
    }

    public TradeLog showTheOrder(String Id){
        return BuyerServerController.showTheOrder(Id);
    }

    public boolean rateTheProduct(String productId , int score){
        return BuyerServerController.rateTheProduct(productId,score);
    }
    public Product getProduct(String productId){
        return BuyerServerController.getInstance().getProduct(productId);
    }
    public Cart getCart(){
        Buyer buyer = (Buyer)getLoggedInPerson();
        return buyer.getCart();

    }
}