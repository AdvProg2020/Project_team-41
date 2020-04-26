package Client.Controller;

import Client.Models.Category;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Server.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterController {
    private static FilterController single_instance = null;
    public static FilterController getInstance()
    {
        if (single_instance == null)
            single_instance = new FilterController();

        return single_instance;
    }

    private Category filterCategory ;
    private String name;
    private String companyName;
    private int price;
    private Seller seller;
    private boolean isThereMore;
//    private HashMap<String ,String> filterFeature;

    private FilterController(){
    }
    public void setFilterCategory(String filterCategoryName) throws Exception {
        this.filterCategory =  Database.getCategoryByName(filterCategoryName);
    }

    public Category getFilterCategory() {
        return filterCategory;
    }

    public ArrayList<Product> getFilteredProducts(){
        return filterCategory.getProducts();
    }
}