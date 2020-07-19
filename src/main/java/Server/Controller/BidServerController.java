package Server.Controller;

import Client.Controller.AllProductsController;
import Client.Models.Bid;
import Client.Models.Person.Buyer;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Server.Database;

import javax.xml.crypto.Data;
import java.util.Date;


public class BidServerController {
    private static BidServerController single_instance = null;
    public static BidServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new BidServerController();

        return single_instance;
    }
    private BidServerController(){
    }
    public void addBid(String productId , String date , Seller seller) throws Exception {
        Product product = AllProductsServerController.getInstance().getProduct(productId);
        for (Bid bid : Database.getInstance().getAllBids()) {
            if(bid.getProduct().equals(product))
                throw new Exception("This product is already in a bid");
        }
        String[] dateTime = {date.split("-")[0] , date.split("-")[1]};
        Date exactEndDate = TimeControl.getDateByDateTime(dateTime);
        Database.getInstance().addBid(new Bid(product , exactEndDate , seller));
    }

    public void addParticipant(String bidId, Buyer buyer, int price) throws Exception {
            Database.getInstance().getBidById(bidId).getBuyer_recommendedPrice().put(buyer , price);

    }

    public void IncreasePrice(Bid bid, Buyer buyer, int price) throws Exception {
        bid.getBuyer_recommendedPrice().replace(buyer , price);

    }
}
