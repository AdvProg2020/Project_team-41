package Client.Models;

import Client.Models.Person.Buyer;
import Client.Models.Person.Seller;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

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
    private boolean isItInOff;
    private Category category;
    private HashMap<String, SpecialFeature> specialFeatures = new HashMap<>();
    private String description;
    private ArrayList<Score>scores = new ArrayList<>();
    private ArrayList<Comment>comments = new ArrayList<>();
    private int views = 0;

    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    //for generating token

    public Product(String name, String companyName, int price, Seller seller, int quantity, Category category, HashMap<String, SpecialFeature> specialFeatures, String description) {
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

    public Product cloneProduct(){
        Product product = this;
        Product clonedProduct = new Product(product.getName(),product.getCompanyName(),product.getPrice(),product.getSeller(),product.getQuantity(),product.getCategory(),(HashMap<String, SpecialFeature>) product.getSpecialFeatures().clone(),product.getDescription());
        clonedProduct.setProductId(product.getProductId());
        clonedProduct.setBuyers(product.getBuyers());
        clonedProduct.setComments(product.getComments());
        clonedProduct.setScores(product.getScores());
        return clonedProduct;
    }

    public Product(){
        this.productId = generateNewToken();
    }
    public ArrayList<Buyer> buyers = new ArrayList<>();

    public Integer calculateAverageScore() {
        int sum = 0;
        if(scores.size()==0)
            return sum;
        else{
        for (Score score : scores) {
            sum += score.getScore();
        }
        return sum/scores.size();}
    }

    public boolean getIsItInOff() {
        return isItInOff;
    }

    public void setIsItInOff(boolean itInOff) {
        isItInOff = itInOff;
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
    public void addComment(Comment comment){
        this.comments.add(comment);
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

    public void decreaseQuantity(int quantity) throws Exception {
        if(this.quantity > 0)
            this.quantity -= quantity;
        else
            throw new Exception("out of stock");
    }

    public void setProductSituation(Situation productSituation) {
        this.productSituation = productSituation;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void setBuyers(ArrayList<Buyer> buyers) {
        this.buyers = buyers;
    }

    public void setSpecialFeatures(HashMap<String, SpecialFeature> specialFeatures) {

        this.specialFeatures = specialFeatures;
    }

    public static String generateNewToken() {
        byte[] randomBytes = new byte[2];
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
                ", seller=" + seller.getUserName() +
                ", quantity=" + quantity
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return price == product.price &&
                quantity == product.quantity &&
                isItInOff == product.isItInOff &&
                views == product.views &&
                Objects.equals(productId, product.productId) &&
                productSituation == product.productSituation &&
                Objects.equals(name, product.name) &&
                Objects.equals(companyName, product.companyName) &&
                Objects.equals(seller, product.seller) &&
                Objects.equals(category, product.category) &&
                Objects.equals(specialFeatures, product.specialFeatures) &&
                Objects.equals(description, product.description) &&
                Objects.equals(scores, product.scores) &&
                Objects.equals(comments, product.comments) &&
                Objects.equals(buyers, product.buyers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productSituation, name, companyName, price, seller, quantity, isItInOff, category, specialFeatures, description, scores, comments, views, buyers);
    }
}
