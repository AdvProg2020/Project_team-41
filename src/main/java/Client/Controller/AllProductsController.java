package Client.Controller;

public class AllProductsController {
    private static AllProductsController single_instance = null;
    public static AllProductsController getInstance()
    {
        if (single_instance == null)
            single_instance = new AllProductsController();

        return single_instance;
    }
    private AllProductsController(){
    }



}
