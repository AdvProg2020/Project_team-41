package Client.Models;

import Client.Models.Person.Seller;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {

    private String productId;
    private Situation productSituation;
    // start of common specifics
    private String name;
    private String companyName;
    private int price;
    private Seller seller;
    private boolean isThereMore;
    // end of common specifics

    private Category category;
    private HashMap<String, String> specialFeatures;//todo new in constructor
    private String description;
    private ArrayList<Score>scores;//todo add method to calculate average
    private ArrayList<Comment>comments;
    private int views;


    public String getProductId() {
        return productId;
    }


    public Situation getProductSituation() {
        return productSituation;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getPrice() {
        return price;
    }

    public Seller getSeller() {
        return seller;
    }

    public boolean isThereMore() {
        return isThereMore;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public int getViews() {
        return views;
    }

}
