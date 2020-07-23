package Client.Controller;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Bid;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.Buyer;
import Client.View.Menus.UserSectionMenus.UserSection;

import java.util.ArrayList;

public class BidController {
    public static void participateBuyerInBid(String bidId , Buyer buyer , int price) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{bidId , buyer.getUserName() , price} , MessageType.ADD_PARTICIPANT));
    }

    public static void increasePrice(Bid bid, int price) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{bid.getBidId() , UserSectionController.getLoggedInPerson().getUserName() , price} , MessageType.INCREASE_PRICE));
    }

    public static ArrayList<Bid> getBidsHeWon(String userName) throws Exception {
       return (ArrayList<Bid>) Connector.getInstance().initializeMessage(new Message(new Object[]{userName} , MessageType.GET_BIDS_HE_WON));
    }

    public static void payForProductInBid(String bidId , int quantity) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{bidId , quantity} , MessageType.PAY_FOR_WON_PRODUCT_IN_BID));
    }

}
