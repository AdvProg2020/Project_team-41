package Client.Models;

import Client.Models.Person.Buyer;
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
    private int quantity;
    // end of common specifics

    private Category category;
    private HashMap<String, SpecialFeature> specialFeatures = new HashMap<>();
    private String description;
    private ArrayList<Score>scores = new ArrayList<>();
    private ArrayList<Comment>comments = new ArrayList<>();
    private int views = 0;

    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    //for generating token

    public Product( String name, String companyName, int price, Seller seller, int quantity, Category category, HashMap<String, SpecialFeature> specialFeatures, String description) {
        this.productId = generateNewToken();
        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.seller = seller;
        this.quantity = quantity;
        this.category = category;
        this.specialFeatures = specialFeatures;
        this.description = description;
    }
    public Product(){
        this.productId = generateNewToken();
    }
    public ArrayList<Buyer> buyers = new ArrayList<>();

    public Integer calculateAverageScore() {
        if(scores.size() == 0)
            return 0;
        int sum = 0;
        if(scores.size()==0)
            return sum;
        else{
        for (Score score : scores) {
            sum += score.getScore();
        }
        return sum/scores.size();}
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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

    public void setPrice(int price) throws Exception {
        if(price < 0)
            throw new Exception("price cant be negative!");
        this.price = price;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setQuantity(int quantity) throws Exception {
        if(quantity < 0)
            throw new Exception("quantity can't be negative!");
        this.quantity = quantity;

    }

    public HashMap<String, SpecialFeature> getSpecialFeatures() {
        return specialFeatures;
    }

    public String getProductId() {
        return productId;
    }

    public ArrayList<Comment> getComments() {
        return comments;
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

    public Integer getPrice() {
        return price;
    }

    public Seller getSeller() {
        return seller;
    }

    public int getQuantity() {
        return quantity;
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
    public void addScore(Score score){
        scores.add(score);
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(int views) throws Exception {
        if(views < 0)
            throw new Exception("views can't be negative!");
        this.views = views;
    }

    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public void addBuyer(Buyer buyer){
        buyers.add(buyer);
    }

    public void decreaseQuantity() throws Exception {
        if(quantity > 0)
            quantity--;
        else
            throw new Exception("out of stock");
    }

    public static String generateNewToken() {
        byte[] randomBytes = new byte[4];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    @Override
    public String toString() {
        return
                "productSituation=" + productSituation +
                ", name='" + name + '\'' +
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                ", seller=" + seller +
                ", quantity=" + quantity
                ;
    }
}
