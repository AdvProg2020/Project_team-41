package Client.Controller.UserSectionController.BuyerAccountController;

public class CartController {
    private static CartController single_instance = null;
    public static CartController getInstance()
    {
        if (single_instance == null)
            single_instance = new CartController();

        return single_instance;
    }
    private CartController(){
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
