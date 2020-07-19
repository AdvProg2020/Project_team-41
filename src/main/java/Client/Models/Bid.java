package Client.Models;

import Client.Controller.Connector;
import Client.Models.BidChat.BidChatBox;
import Client.Models.BidChat.BidChatComment;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.Buyer;
import Client.Models.Person.Seller;
import Server.Controller.AllCommands;
import Server.Controller.RandomNumberGenerator;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Bid implements Serializable {
    private String bidId;
    private Product product;
    private Date endDate;
    private Seller seller;
    private HashMap<Buyer, Integer> buyer_recommendedPrice;
    private BidChatBox bidChatBox;

    public Bid(Product product, Date endDate, Seller seller) {
        this.bidId = RandomNumberGenerator.getToken(5);
        this.product = product;
        this.endDate = endDate;
        this.seller = seller;
        buyer_recommendedPrice = new HashMap<>();
        this.bidChatBox=new BidChatBox(this.bidId);
    }

    public Product getProduct() {
        return product;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public HashMap<Buyer, Integer> getBuyer_recommendedPrice() {
        return buyer_recommendedPrice;
    }

    public void setBuyer_recommendedPrice(HashMap<Buyer, Integer> buyer_recommendedPrice) {
        this.buyer_recommendedPrice = buyer_recommendedPrice;
    }

    public static ArrayList<Bid> getAllBids() throws Exception {
        return (ArrayList<Bid>) Connector.getInstance().initializeMessage(new Message(MessageType.GET_ALL_BIDS));
    }

    @Override
    public String toString() {
        return "Bid{" +
                "product=" + product +
                ", endDate=" + endDate +
                '}';
    }

    public void increasePrice(Buyer buyer, int price) {
        buyer_recommendedPrice.replace(buyer , price);

    }

    public BidChatBox getBidChatBox() {
        return bidChatBox;
    }
    public void addComment(BidChatComment bidChatComment){
        bidChatBox.addComment(bidChatComment);
    }
}
