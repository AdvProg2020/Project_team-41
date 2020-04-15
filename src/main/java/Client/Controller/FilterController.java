package Client.Controller;

import Client.Models.Product;

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
    public ArrayList<String> getAvailableFilters(){

    }
    public ArrayList<Product> getFilteredProducts(String Filter){

    }
    public ArrayList<String> getCurrentFilters(){

    }
    public ArrayList<Product> getProductsWithDisabledFilter(String Filter){

    }


}
