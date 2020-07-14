package Client.Models;

import Client.Models.Person.Buyer;
import Client.Models.Person.Seller;
import Server.Controller.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Bid {
    private String bidId;
    private Product product;
    private Date endDate;
    private Seller seller;
    private HashMap<Buyer, Integer> buyer_recommendedPrice;

    public Bid(Product product, Date endDate, Seller seller) {
        this.bidId = RandomNumberGenerator.getToken(5);
        this.product = product;
        this.endDate = endDate;
        this.seller = seller;
    }

}