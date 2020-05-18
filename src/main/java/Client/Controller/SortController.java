package Client.Controller;

import Client.Models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortController {

    private static SortController single_instance = null;

    public static SortController getInstance() {
        if (single_instance == null)
            single_instance = new SortController();

        return single_instance;
    }

    private SortController() {
    }

    private String sortFeature = "view";

    public String getSortFeature() {
        return sortFeature;
    }

    public void setSortFeature(String sortFeature) throws NullPointerException {
        if(sortFeature.equalsIgnoreCase("product name")||
        sortFeature.equalsIgnoreCase("company name")||
        sortFeature.equalsIgnoreCase("price")||
        sortFeature.equalsIgnoreCase("seller username")||
        sortFeature.equalsIgnoreCase("score")||
       sortFeature.equalsIgnoreCase("view"))
        this.sortFeature = sortFeature;
        else
            throw new NullPointerException("please enter a valid sort type:");
    }

    public List<Product> getSortedProducts(boolean offOrNot) {
        List<Product> sortedFilter = FilterController.getInstance().filterProducts(offOrNot);
        switch (sortFeature) {
            case "product name": {
                Collections.sort(sortedFilter, new ByName());
                break;
            }
            case "company name": {
                Collections.sort(sortedFilter, new ByCompanyName());
                break;
            }
            case "price": {
                Collections.sort(sortedFilter, new ByPrice());
                break;
            }
            case "seller username": {
                Collections.sort(sortedFilter, new BySellerUsername());
                break;
            }
            case "score": {
                Collections.sort(sortedFilter, new ByScore());
                break;
            }
            default:
                Collections.sort(sortedFilter, new ByView());
        }
        return sortedFilter;
    }
}

class ByName implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class ByCompanyName implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getCompanyName().compareTo(o2.getCompanyName());
    }
}

class ByPrice implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }
}

class BySellerUsername implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getSeller().getUserName().compareTo(o2.getSeller().getUserName());
    }
}

class ByScore implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.calculateAverageScore().compareTo(o2.calculateAverageScore());
    }
}

class ByView implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getViews().compareTo(o1.getViews());
    }
}