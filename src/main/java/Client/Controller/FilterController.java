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
    // -1 for not important ir filtered:
    private int definitePrice =-1;
    private Pair<Integer, Integer> priceMinMax;
    private String sellerUserName;
    //1 for exist ... 0 for not exist ... -1 for not important or filtered:
    private int existence = -1;
    private HashMap<String, String> filterFeature;

    private FilterController() {
    }

    public List<Product> filterProducts() {

        return Database.getAllProducts().stream()
                .filter(Product -> {
                    {
                        if (name != null) {
                            if (!Product.getName().equals(name))
                                return false;
                        }
                    }
                    {
                        if (companyName != null) {
                            if (!Product.getCompanyName().equals(companyName))
                                return false;
                        }
                    }
                    {
                        if (definitePrice != -1) {
                            if (Product.getPrice() != definitePrice)
                                return false;
                        }
                    }
                    {
                        if (priceMinMax != null) {
                            if (priceMinMax.getKey() > Product.getPrice() || Product.getPrice() > priceMinMax.getValue())
                                return false;
                        }
                    }
                    {
                        if (sellerUserName != null) {
                            if (!Product.getSeller().getUserName().equals(sellerUserName))
                                return false;
                        }
                    }
                    {
                        if (existence != -1) {
                            boolean isThereMore;
                            if (existence == 1)
                                isThereMore = true;
                            else
                                isThereMore = false;
                            if (Product.isThereMore() != isThereMore)
                                return false;
                        }
                    }
                    {

                        if (filterCategory != null) {
                            if (!Product.getCategory().getName().equals(filterCategory.getName()))
                                return false;
                        }
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    public void disableFilter(String filterToBeDisabled) throws Exception {
        switch (filterToBeDisabled) {
            case "product name": {
                name = null;
                break;
            }
            case "company name": {
                companyName = null;
                break;
            }
            case "category name": {
                filterCategory = null;
                break;
            }
            case "price" :{
                definitePrice = -1;
                priceMinMax = null;
                break;
            }
            case "seller": {
                sellerUserName = null;
                break;
            }
            case "existence": {
                existence = -1;
                break;
            }
            default:
                throw new Exception("invalid filter");
        }
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

    public void setDefinitePrice(int definitePrice) throws Exception {
        if (definitePrice >= 0)
            this.definitePrice = definitePrice;
        else
            throw new Exception("price should be a positive number");
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

}