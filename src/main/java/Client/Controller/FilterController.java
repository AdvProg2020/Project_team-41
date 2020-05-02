package Client.Controller;

import Client.Models.Category;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.Models.SpecialFeature;
import Server.Controller.AllProductsServerController;
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
    private int definitePrice = -1;
    private Pair<Integer, Integer> priceMinMax;
    private String sellerUserName;
    //1 for exist ... 0 for not exist ... -1 for not important or filtered:
    private int existence = -1;
    private HashMap<String, String> definiteStringFeatures = new HashMap<>();
    private HashMap<String, Integer> definiteIntFeatures = new HashMap<>();
    private HashMap<String, Pair<Integer, Integer>> rangeFeatures = new HashMap<>();

    private FilterController() {
    }

    public List<Product> filterProducts() {
        return AllProductsServerController.getInstance().getAllProducts().stream()
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
                    {
                        if (!definiteStringFeatures.isEmpty()) {
                            for (String featureNameGiven : definiteStringFeatures.keySet()) {
                                for (String productFeatureName : Product.getSpecialFeatures().keySet()) {
                                    if (featureNameGiven.equals(productFeatureName)) {
                                        if (!Product.getSpecialFeatures().get(productFeatureName).getSpecialFeatureString()
                                                .equals(definiteStringFeatures.get(featureNameGiven))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        if (!definiteIntFeatures.isEmpty()) {
                            for (String featureNameGiven : definiteIntFeatures.keySet()) {
                                for (String productFeatureName : Product.getSpecialFeatures().keySet()) {
                                    if (featureNameGiven.equals(productFeatureName)) {
                                        if (Product.getSpecialFeatures().get(productFeatureName).getSpecialFeatureInt()
                                                != definiteIntFeatures.get(featureNameGiven)) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        if (!rangeFeatures.isEmpty()) {
                            for (String featureNameGiven : rangeFeatures.keySet()) {
                                for (String productFeatureName : Product.getSpecialFeatures().keySet()) {
                                    if (featureNameGiven.equals(productFeatureName)) {
                                        if (Product.getSpecialFeatures().get(productFeatureName).getSpecialFeatureInt()
                                                < rangeFeatures.get(featureNameGiven).getKey() ||
                                                Product.getSpecialFeatures().get(productFeatureName).getSpecialFeatureInt()
                                                        > rangeFeatures.get(featureNameGiven).getValue()) {
                                            return false;
                                        }
                                    }
                                }
                            }
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
            case "price": {
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

    public void disableSpecialFeature(String feature) throws ClassNotFoundException, NullPointerException {
        if (isTheFeatureNumeric(feature)) {
            definiteIntFeatures.clear();
            rangeFeatures.clear();
        } else
            definiteStringFeatures.clear();
    }

    public boolean isTheFeatureNumeric(String featureNameToFind) throws ClassNotFoundException, NullPointerException {
        if (FilterController.getInstance().getFilterCategory().getProducts().size() == 0) {
            throw new ClassNotFoundException("Category doest't have any product");
        } else {
            for (String specialFeatureName : FilterController.getInstance().getFilterCategory().getSpecialFeatures()) {
                if (specialFeatureName.equals(featureNameToFind)) {
                    if (FilterController.getInstance().getFilterCategory().getProducts().get(0).getSpecialFeatures().get(specialFeatureName).StringOrInt().equals("int"))
                        return true;
                    else
                        return false;
                }
            }
            throw new NullPointerException("NO feature found with this name");
        }
    }

    public HashMap<String, String> getDefiniteStringFeatures() {
        return definiteStringFeatures;
    }

    public void setDefiniteStringFeatures(HashMap<String, String> definiteStringFeatures) {
        this.definiteStringFeatures = definiteStringFeatures;
    }

    public HashMap<String, Integer> getDefiniteIntFeatures() {
        return definiteIntFeatures;
    }

    public void setDefiniteIntFeatures(HashMap<String, Integer> definiteIntFeatures) {
        this.definiteIntFeatures = definiteIntFeatures;
    }

    public HashMap<String, Pair<Integer, Integer>> getRangeFeatures() {
        return rangeFeatures;
    }

    public void setRangeFeatures(HashMap<String, Pair<Integer, Integer>> rangeFeatures) {
        this.rangeFeatures = rangeFeatures;
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



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

         if (filterCategory != null)
            result.append("\n" + "filterCategory: " + filterCategory.getName());
         if (name != null)
            result.append("\n" + "name: " + name);
         if (companyName != null)
            result.append("\n" + "companyName: " + companyName);
         if (definitePrice != -1)
            result.append("\n" + "price: " + definitePrice);
         if (priceMinMax != null)
            result.append("\n" + "price range: between " + priceMinMax.getKey() + " and " + priceMinMax.getValue());
         if (sellerUserName != null)
            result.append("\n" + "seller username: " + sellerUserName);
         if (existence != -1) {
            if (existence == 1)
                result.append("\n" + "existence: " + "should be exist");
            else
                result.append("\n" + "existence: " + "should NOT be exist");
        }
         if (!definiteStringFeatures.isEmpty()) {
            result.append("\n");
            for (String featureName : definiteStringFeatures.keySet()) {
                result.append("feature name: " + featureName + "/ feature value: " + definiteStringFeatures.get(featureName) + "     ");
            }
        }
         if (!definiteIntFeatures.isEmpty()) {
            result.append("\n");
            for (String featureName : definiteIntFeatures.keySet()) {
                result.append("feature name: " + featureName + "/ feature value: " + definiteStringFeatures.get(featureName) + "     ");
            }
        }
         if (!rangeFeatures.isEmpty()) {
            result.append("\n");
            for (String featureName : rangeFeatures.keySet()) {
                result.append("feature name: " + featureName + "/ feature value: between " + rangeFeatures.get(featureName).getKey() + " and " + rangeFeatures.get(featureName).getValue() + "    ");
            }
        }
        return result.toString();
    }
    public static void resetFilterController(){
        single_instance=null;
    }
}