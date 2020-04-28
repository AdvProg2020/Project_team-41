package Client.Models;

import Client.Models.Person.Seller;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
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

    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    //for generating token

    public Product( String name, String companyName, int price, Seller seller, boolean isThereMore, Category category, HashMap<String, SpecialFeature> specialFeatures, String description) {
        this.productId = generateNewToken();
        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.seller = seller;
        this.isThereMore = isThereMore;
        this.category = category;
        this.specialFeatures = specialFeatures;
        this.description = description;
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
    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
