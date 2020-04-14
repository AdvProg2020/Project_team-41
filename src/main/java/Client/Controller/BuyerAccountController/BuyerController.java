package Client.Controller.BuyerAccountController;

import Client.Models.Person.Buyer;
import Client.Models.TradeLogs;

public class BuyerController {

    public static boolean checkDiscountCode(String code){
        //TODO checking the validation
        return true;
    }

    public static boolean payForTheShop(){
        //TODO paying process
        return true;
    }

    public static TradeLogs showTheOrder(String Id){
        //TODO find the order and for example:
        return new TradeLogs();
    }

    public static boolean rateTheProduct(String productId , int score){
        //TODO process and return false if the buyer hadn't bought the product
        return true;
    }
}
