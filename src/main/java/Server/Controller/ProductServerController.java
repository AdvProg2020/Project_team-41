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

    public static void  addToCart(String Id){

        //TODO add the product to cart
    }

    public static void addComment(String title , String content){
        //TODO add the comment
    }

}