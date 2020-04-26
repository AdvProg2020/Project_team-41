package Client.Controller;

import Client.Models.Category;
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

    private Category filterCategory = null;
    private HashMap<String ,String> filterFeature;

    private FilterController(){
    }
    public void setFilterCategory(String filterCategoryName) throws Exception {
        this.filterCategory =  Database.getCategoryByName(filterCategoryName);
    }

    public void setFilterFeature(HashMap<String, String> filterFeature) {
        this.filterFeature = filterFeature;
    }

    public Category getFilterCategory() {
        return filterCategory;
    }

    public HashMap<String, String> getFilterFeature() {
        return filterFeature;
    }

    public ArrayList<Product> getFilteredProducts(){
        return filterCategory.getProducts();
    }
}