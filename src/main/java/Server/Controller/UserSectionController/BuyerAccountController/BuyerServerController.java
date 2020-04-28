package Server.Controller.UserSectionController.BuyerAccountController;

import Client.Models.TradeLog;

public class BuyerServerController {

    private static BuyerServerController single_instance = null;
    public static BuyerServerController getInstance(){
        if (single_instance == null)
            single_instance = new BuyerServerController();

        return single_instance;
    }
    private BuyerServerController(){
    }

    public static boolean checkDiscountCode(String code){
        //TODO checking the validation
        return true;
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
}
