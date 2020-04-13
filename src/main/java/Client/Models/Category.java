package Client.Models;

import java.util.ArrayList;

public class Category {
    private static ArrayList<Category> allCategory = new ArrayList<>();
    private String name;
    private ArrayList<String> specialFeatures;//todo new in constructor
    private ArrayList<Product>products;

    public static Category getCategoryByName(String CategoryName) {
        //todo
        Category example = null;
        return example;
    }


}
