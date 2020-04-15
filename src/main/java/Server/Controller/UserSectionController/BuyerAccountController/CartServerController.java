package Server.Controller.UserSectionController.BuyerAccountController;

public class CartServerController {
    private static CartServerController single_instance = null;
    public static CartServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new CartServerController();

        return single_instance;
    }
    private CartServerController(){
    }
    public static void increaseProduct(int num , String productId){
        //TODO increase number of the product
    }

    public static void decreaseProduct(int num , String productId){
        //TODO decrease number of the product
    }
    public static int calculateTotalPrice(){
        //TODO calculate the price
        int price = -1;
        return price;
    }

}
