package Server.Controller;

public class ProductServerController {
    private static ProductServerController single_instance = null;
    public static ProductServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new ProductServerController();

        return single_instance;
    }
    private ProductServerController(){
    }

    public static boolean addToCart(String Id){

        //TODO add the product to cart
        //Return false if the user is not logged in
        return true;
    }

    public static void addComment(String title , String content){
        //TODO add the comment
    }

}
