package Client.Controller;

import Client.View.Menus.Product;

import java.util.ArrayList;

public class FilterController {
    private static FilterController single_instance = null;
    public static FilterController getInstance()
    {
        if (single_instance == null)
            single_instance = new FilterController();

        return single_instance;
    }
    private FilterController(){
    }
}
