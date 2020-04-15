package Client.Controller;

import Client.View.Menus.Product;

import java.util.ArrayList;

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
