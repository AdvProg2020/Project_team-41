package Client.Controller;

public class ProductController {
    private static ProductController single_instance = null;
    public static ProductController getInstance()
    {
        if (single_instance == null)
            single_instance = new ProductController();

        return single_instance;
    }
    private ProductController(){
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
