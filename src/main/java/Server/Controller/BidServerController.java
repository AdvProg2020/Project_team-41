package Server.Controller;

import Client.Controller.AllProductsController;
import Client.Models.Bid;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Server.Database;

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
        Product product = AllProductsController.getInstance().getProduct(productId);
        String[] dateTime = {date.split("-")[0] , date.split("-")[0]};
        Date exactEndDate = TimeControl.getDateByDateTime(dateTime);
        Database.getInstance().getAllBids().add(new Bid(product , exactEndDate , seller));
    }
}
