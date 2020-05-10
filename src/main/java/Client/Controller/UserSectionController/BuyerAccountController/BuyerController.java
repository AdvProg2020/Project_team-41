package Client.Controller.UserSectionController.BuyerAccountController;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.*;
import Client.Models.Person.Buyer;
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
    public ArrayList<String> getCodedDiscounts(){
        return loggedInPerson.getDiscountCodes();
    }

    public void payForTheShop() throws Exception {
        BuyerServerController.payForTheShop((Buyer)loggedInPerson);
    }

    public ArrayList<TradeLog> getTradeLogs(){
        return loggedInPerson.getTradeLogs();
    }

    public TradeLog showTheOrder(String id){
        for (TradeLog tradeLog : loggedInPerson.getTradeLogs()) {
            if(tradeLog.getLogId().equals(id))
                return tradeLog;
        }
        return null;
    }

    public void rateTheProduct(String productId , int score) throws Exception {
        Score scoreObject = new Score(loggedInPerson,score,getProduct(productId));
        BuyerServerController.rateTheProduct(productId,scoreObject);
    }
    public Product getProduct(String productId) throws Exception {
        return BuyerServerController.getInstance().getProduct(productId);
    }
    public Cart getCart(){
        Buyer buyer = (Buyer)getLoggedInPerson();
        return buyer.getCart();

    }
}