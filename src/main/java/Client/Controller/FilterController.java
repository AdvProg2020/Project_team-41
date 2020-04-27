package Client.Controller;

import Client.Models.Category;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Server.Database;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class FilterController {
    private static FilterController single_instance = null;

    public static FilterController getInstance() {
        if (single_instance == null)
            single_instance = new FilterController();

        return single_instance;
    }

    private Category filterCategory;
    private String name;
    private String companyName;
    private int definitePrice;
    private Pair<Integer, Integer> priceMinMax;
    private Seller seller;
    private boolean isThereMore;
    private HashMap<String, String> filterFeature;

    private FilterController() {
    }

    private List<Product> filterProducts() {
        return Database.getAllProducts().stream()
                .filter(Product -> {
                    if (name != null)
                        return Product.getName().equals(name);
                    else if (companyName != null)
                        return Product.getCompanyName().equals(companyName);
                    //TODO other fields
                    else
                        return true;
                })
                .collect(Collectors.toList());
    }

    public void setFilterCategory(String filterCategoryName) throws Exception {
        this.filterCategory = Database.getCategoryByName(filterCategoryName);
    }

    public Category getFilterCategory() {
        return filterCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getDefinitePrice() {
        return definitePrice;
    }

    public void setDefinitePrice(int definitePrice) {
        this.definitePrice = definitePrice;
    }

    public void setPriceMinMax(Pair<Integer, Integer> priceMinMax) {
        this.priceMinMax = priceMinMax;
    }

    public Pair<Integer, Integer> getPriceMinMax() {
        return priceMinMax;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(String sellerName) throws Exception {
        this.seller = Database.getSellerByName(sellerName);
    }

    public boolean isThereMore() {
        return isThereMore;
    }

    public void setThereMore(boolean thereMore) {
        isThereMore = thereMore;
    }

    public ArrayList<Product> getFilteredProducts() {
        return filterCategory.getProducts();
    }
}