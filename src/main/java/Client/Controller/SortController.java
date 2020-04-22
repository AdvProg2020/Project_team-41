package Client.Controller;

import Client.Models.Product;

import java.util.ArrayList;

public class SortController {
    private static SortController single_instance = null;
    public static SortController getInstance()
    {
        if (single_instance == null)
            single_instance = new SortController();

        return single_instance;
    }
    private SortController(){
    }
    private String sortFeature;

    public String getSortFeature() {
        return sortFeature;
    }

    public void setSortFeature(String sortFeature) {
        this.sortFeature = sortFeature;
    }
    public ArrayList<Product> getProducts(){
        System.err.println("fail");
        return new ArrayList<>();
    }
}
