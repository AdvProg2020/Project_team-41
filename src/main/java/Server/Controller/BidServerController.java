package Server.Controller;

import Client.Controller.AllProductsController;
import Client.Models.Bid;
import Client.Models.Person.Buyer;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.Models.TradeLog;
import Server.Database;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;


public class BidServerController {
    private static BidServerController single_instance = null;

    public static BidServerController getInstance() {
        if (single_instance == null)
            single_instance = new BidServerController();

        return single_instance;
    }

    private BidServerController() {
    }

    public void addBid(String productId, String date, String sellerUsername) throws Exception {
        Product product = AllProductsServerController.getInstance().getProduct(productId);
        for (Bid bid : Database.getInstance().getAllBids()) {
            if (bid.getProduct().equals(product))
                throw new Exception("This product is already in a bid");
        }
        String[] dateTime = {date.split("-")[0], date.split("-")[1]};
        Date exactEndDate = TimeControl.getDateByDateTime(dateTime);
        Database.getInstance().addBid(new Bid(product, exactEndDate, Database.getInstance().getSellerByUsername(sellerUsername)));
    }

    public void addParticipant(String bidId, String buyerId, int price) throws Exception {
        Database.getInstance().getBidById(bidId).getBuyer_recommendedPrice().put((Buyer) Database.getInstance().getPersonByUsername(buyerId), price);
    }

    public void IncreasePrice(String bidId, String buyerId, int price) throws Exception {
        Bid bid = Database.getInstance().getBidById(bidId);
        Buyer buyer = (Buyer) Database.getInstance().getPersonByUsername(buyerId);
        if (price < bid.getBuyer_recommendedPrice().get(buyer)) {
            throw new Exception("New price should be higher than the previous one");
        } else {
            bid.getBuyer_recommendedPrice().replace(buyer, price);
        }
    }

    public void payForProduct(String bidId, int quantity) throws Exception {
        Bid bid = Database.getInstance().getBidById(bidId);
        Product product = bid.getProduct();
        Buyer buyer = bid.getWinnerInfo().getKey();
        Seller seller = bid.getProduct().getSeller();
        if(quantity>product.getQuantity()){
            throw new Exception("we don't have that much products!");
        }
        else{
        int priceToPay = (bid.getWinnerInfo().getValue()) * quantity;
        product.addBuyer(buyer);
        buyer.decreaseCredit(priceToPay);
        product.setQuantity(product.getQuantity() - quantity);
        seller.increaseCreditWithWage(priceToPay);
        HashMap<Product, Integer> productQuantity = new HashMap<>();
            productQuantity.put(product, quantity);
        seller.addTradeLog(new TradeLog(new Date(), priceToPay, 0, productQuantity, buyer.getUserName(), TradeLog.DeliverySituation.WAITING, null));
        buyer.addTradeLog(new TradeLog(new Date(), priceToPay, 0, productQuantity, buyer.getUserName(), TradeLog.DeliverySituation.WAITING, null));}
        bid.setWinnerBoughtProduct(true);
    }
}
