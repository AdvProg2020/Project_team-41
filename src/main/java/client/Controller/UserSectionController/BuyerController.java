package Client.Controller.UserSectionController;

import Client.Controller.Connector;
import Client.Models.*;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.Buyer;
import Client.Models.Person.Person;
import Server.Controller.UserSectionController.BuyerServerController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    public ArrayList<String> getCodedDiscounts() throws Exception {
        return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson}, MessageType.GET_CODED_DISCOUNTS));
//        return BuyerServerController.getInstance().getCodedDiscounts(loggedInPerson);
    }
    public void payForTheShop(boolean walletSelected) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,walletSelected}, MessageType.PAY_FOR_THE_SHOP));
//       BuyerServerController.payForTheShop((Buyer)loggedInPerson);
    }
    public void setReceiverInformation(ArrayList<String> receiverInformation)  {
        Buyer buyer = (Buyer)getLoggedInPerson();
        buyer.getCart().setReceiverInformation(receiverInformation);
    }
    public void addCodedDiscountToCart(String discountCode) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,discountCode}, MessageType.ADD_CODED_DISCOUNT_TO_CART));
//        BuyerServerController.getInstance().addCodedDiscountToCart((Buyer) loggedInPerson,discountCode);
    }
    public void removeCodedDiscountFromCart() throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson}, MessageType.REMOVE_CODED_DISCOUNT_FROM_CART));
//        BuyerServerController.getInstance().removeCodedDiscountFromCart((Buyer) loggedInPerson);
    }
    public ArrayList<TradeLog> getTradeLogs(){
        return loggedInPerson.getTradeLogs();
    }
    public ArrayList<Product> getAllBoughtFiles() throws Exception {
        return (ArrayList<Product>) Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson}, MessageType.GET_ALL_BOUGHT_FILES));
    }

    public List<Byte> downloadFile(Product product) throws Exception {
        return (List<Byte>) Connector.getInstance().initializeMessage(new Message(new Object[]{product}, MessageType.DOWNLOAD_FILE));

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
        Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,productId,scoreObject}, MessageType.RATE_THE_PRODUCT));
//        BuyerServerController.rateTheProduct((Buyer) loggedInPerson,productId,scoreObject);
    }
    public Product getProduct(String productId) throws Exception {
        return (Product) Connector.getInstance().initializeMessage(new Message(new Object[]{productId}, MessageType.GET_PRODUCT_BUYER_SECTION));
//        return BuyerServerController.getInstance().getProduct(productId);
    }
    public Cart getCart() {
        Buyer buyer = (Buyer)getLoggedInPerson();
        return buyer.getCart();

    }
    public ArrayList<String> getCodedDiscount(String discountCode) throws Exception {
        return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(new Object[]{discountCode,loggedInPerson}, MessageType.GET_CODED_DISCOUNT));
//        return BuyerServerController.getInstance().getCodedDiscount(discountCode, loggedInPerson);
    }
    public void increaseProduct(int num , String productId) throws Exception {
        Buyer buyer = (Buyer) loggedInPerson;
        Connector.getInstance().initializeMessage(new Message(new Object[]{buyer,num,productId}, MessageType.INCREASE_PRODUCT));
//        BuyerServerController.getInstance().increaseProduct(buyer,num,productId);
    }
    public void decreaseProduct(int num , String productId) throws Exception {
        Buyer buyer = (Buyer) loggedInPerson;
        Connector.getInstance().initializeMessage(new Message(new Object[]{buyer,num,productId}, MessageType.DECREASE_PRODUCT));
//        BuyerServerController.getInstance().decreaseProduct(buyer,num,productId);
    }
    public int calculateTotalPrice(){
        Buyer buyer = (Buyer) loggedInPerson;
        int cashToPay;
        Cart cart = buyer.getCart();
        if(cart.getCodedDiscount() == null)
            cashToPay = cart.totalPrice();
        else {
            cashToPay = cart.getCodedDiscount().howMuchWillItCost(cart.totalPrice());
        }
        return cashToPay;
    }
}