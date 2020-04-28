package Client.Models;

import Client.Models.Person.Seller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Product implements Serializable {

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
    private HashMap<String, SpecialFeature> specialFeatures = new HashMap<>();//todo new in constructor
    private String description;
    private ArrayList<Score>scores;//todo add method to calculate average
    private ArrayList<Comment>comments;
    private int views;

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setThereMore(boolean thereMore) {
        isThereMore = thereMore;
    }

    public HashMap<String, SpecialFeature> getSpecialFeatures() {
        return specialFeatures;
    }

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
