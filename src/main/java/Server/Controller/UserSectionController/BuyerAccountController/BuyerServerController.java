package Server.Controller.UserSectionController.BuyerAccountController;

import Client.Models.CodedDiscount;
import Client.Models.Product;
import Client.Models.TradeLog;

import java.util.ArrayList;

public class BuyerServerController {

    private static BuyerServerController single_instance = null;
    public static BuyerServerController getInstance(){
        if (single_instance == null)
            single_instance = new BuyerServerController();

        return single_instance;
    }
    private BuyerServerController(){
    }

    public static ArrayList<CodedDiscount> getCodedDiscounts(){
        //TODO checking the validation
        return null;
    }

    public static boolean payForTheShop(){
        //TODO paying process
        return true;
    }

    public static TradeLog showTheOrder(String Id){
        //TODO find the order and for example:
        return null;
    }

    public static boolean rateTheProduct(String productId , int score){
        //TODO process and return false if the buyer hadn't bought the product
        return true;
    }
    public Product getProduct(String productId){

    }

}
