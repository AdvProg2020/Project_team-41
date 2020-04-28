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
    private int definitePrice ;
    private Pair<Integer, Integer> priceMinMax;
    private String sellerUserName;
    //1 for exist ... 0 for not exist ... -1 for not important or filtered
    private int existence = -1;
    private HashMap<String, String> filterFeature;

    private FilterController() {
    }

    public List<Product> filterProducts() {
        boolean tOrF = true;
        return Database.getAllProducts().stream()
                .filter(Product -> {
                    {
                        if (name != null){
                             if(!Product.getName().equals(name))
                                 return false;}
                    }
                    { if (companyName != null){
                        if(!Product.getCompanyName().equals(companyName))
                            return false;}
                    }
                    {
                        if (definitePrice != 0){
                            if(Product.getPrice() != definitePrice)
                                return false;
                        }
                    }
                    {
                        if (priceMinMax != null){
                            if(priceMinMax.getKey()>Product.getPrice() || Product.getPrice()>priceMinMax.getValue())
                                return false;
                        }
                    }
                    {
                        if (sellerUserName != null){
                            if(!Product.getSeller().getUserName().equals(sellerUserName))
                                return false;
                        }
                    }
                    {
                        if (existence != -1){
                            boolean isThereMore;
                            if(existence==1)
                                isThereMore=true;
                            else
                                isThereMore=false;
                            if(Product.isThereMore() != isThereMore)
                                return false;
                        }
                    }
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

    public String getSellerUserName() {
        return sellerUserName;
    }

    public void setSellerUserName(String sellerUserName) throws Exception {
      Database.getSellerByUsername(sellerUserName);
        this.sellerUserName = sellerUserName;
    }

    public void setExistence(int existence) {
        this.existence = existence;
    }

    public ArrayList<Product> getFilteredProducts() {
        return filterCategory.getProducts();
    }
}