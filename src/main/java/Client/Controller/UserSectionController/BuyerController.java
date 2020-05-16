package Client.Controller.UserSectionController;

import Client.Models.*;
import Client.Models.Person.Buyer;
import Server.Controller.UserSectionController.BuyerServerController;
import Server.Database;

import java.util.ArrayList;

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
        return BuyerServerController.getInstance().getCodedDiscounts(loggedInPerson);
    }

    public void payForTheShop() throws Exception {
        BuyerServerController.payForTheShop((Buyer)loggedInPerson);
    }
    public void setReceiverInformation(ArrayList<String> receiverInformation){
        Buyer buyer = (Buyer)getLoggedInPerson();
        buyer.getCart().setReceiverInformation(receiverInformation);
    }
    public void addCodedDiscountToCart(String discountCode) throws Exception {
        BuyerServerController.getInstance().addCodedDiscountToCart((Buyer) loggedInPerson,discountCode);
    }

    public ArrayList<TradeLog> getTradeLogs(){
        return loggedInPerson.getTradeLogs();
    }

    public TradeLog showTheOrder(String id) throws Exception {
        for (TradeLog tradeLog : loggedInPerson.getTradeLogs()) {
            if(tradeLog.getLogId().equals(id))
                return tradeLog;
        }
        throw new Exception("wrong order Id");
    }

    public void rateTheProduct(String productId , int score) throws Exception {
        Score scoreObject = new Score(loggedInPerson,score,getProduct(productId));
        BuyerServerController.rateTheProduct((Buyer) loggedInPerson,productId,scoreObject);
    }
    public Product getProduct(String productId) throws Exception {
        return BuyerServerController.getInstance().getProduct(productId);
    }
    public Cart getCart(){
        Buyer buyer = (Buyer)getLoggedInPerson();
        return buyer.getCart();

    }
    public void increaseProduct(int num , String productId) throws Exception {
        Buyer buyer = (Buyer) loggedInPerson;
        BuyerServerController.getInstance().increaseProduct(buyer,num,productId);
    }

    public void decreaseProduct(int num , String productId) throws Exception {
        Buyer buyer = (Buyer) loggedInPerson;
        BuyerServerController.getInstance().decreaseProduct(buyer,num,productId);
    }
    public int calculateTotalPrice(){
        Buyer buyer = (Buyer) loggedInPerson;
        return ((Buyer) loggedInPerson).getCart().totalPrice();
    }
}