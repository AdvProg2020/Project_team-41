package Client.Models;

import Client.Models.Person.Buyer;
import Client.Models.Person.Seller;
import Server.Controller.AllCommands;
import Server.Controller.RandomNumberGenerator;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.*;

public class Product implements Serializable {

    private String productId;
    private Situation situation;
    // start of common specifics
    private String name;
    private String companyName;
    private int price;
    private Seller seller;
    private int quantity;
    // end of common specifics
    private Off off;
    private Category category;
    private HashMap<String, SpecialFeature> specialFeatures = new HashMap<>();
    private String description;
    private ArrayList<Score>scores = new ArrayList<>();
    private ArrayList<Comment>comments = new ArrayList<>();
    private int views = 0;


    public Product(String name, String companyName, int price, Seller seller, int quantity, Category category, HashMap<String, SpecialFeature> specialFeatures, String description) throws Exception {
        this.productId = RandomNumberGenerator.getToken(5);
        this.situation = Situation.CREATING;
        this.setName(name);
        this.setCompanyName(companyName);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setDescription(description);
        this.category = category;
        this.setSpecialFeatures(specialFeatures);
        this.description = description;
    }

    public Product cloneProduct() throws Exception {
        Product product = this;
        Product clonedProduct = new Product(product.getName(),product.getCompanyName(),product.getPrice(),product.getSeller(),product.getQuantity(),product.getCategory(),(HashMap<String, SpecialFeature>) product.getSpecialFeatures().clone(),product.getDescription());
        clonedProduct.setOff(product.getOff());
        clonedProduct.setProductId(product.getProductId());
        clonedProduct.setBuyers(product.getBuyers());
        clonedProduct.setComments(product.getComments());
        clonedProduct.setScores(product.getScores());
        return clonedProduct;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public Product(){
        this.productId = RandomNumberGenerator.getToken(5);
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

    public boolean isItInOff() {
        return off != null;
    }

    public void setOff(Off off) {
        this.off = off;
        ServerSaver.write(AllCommands.allData);
    }

    public void setProductId(String productId) {
        this.productId = productId;
        ServerSaver.write(AllCommands.allData);
    }

    public void setCategory(Category category) {
        this.category = category;
        ServerSaver.write(AllCommands.allData);
    }

    public void setDescription(String description) {

        this.description = description;
        ServerSaver.write(AllCommands.allData);
    }

    public void setName(String name) throws Exception {
        if(name.isBlank())
            throw new Exception("product name can't be blank!");
        this.name = name;

        ServerSaver.write(AllCommands.allData);
    }

    public void setCompanyName(String companyName) throws Exception {
        if(companyName.isBlank())
            throw new Exception("company name can't be blank!");
        this.companyName = companyName;
        ServerSaver.write(AllCommands.allData);
    }

    public void setPrice(int price) throws Exception {
        if(price < 0)
            throw new Exception("price can't be negative!");
        this.price = price;
        ServerSaver.write(AllCommands.allData);
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
        ServerSaver.write(AllCommands.allData);
    }

    public void setQuantity(int quantity) throws Exception {
        if(quantity < 0)
            throw new Exception("quantity can't be negative!");
        this.quantity = quantity;
        ServerSaver.write(AllCommands.allData);

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

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getPrice() {
        return price;
    }

    public Integer getPriceWithOff(){
        if(isItInOff()) {
            if (new Date().after(off.getStartDate()) && new Date().before(off.getEndDate()))
                return price * (off.getAmountOfDiscount() / 100);
        }
        return price;
    }

    public Off getOff() {
        return off;
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
        ServerSaver.write(AllCommands.allData);
    }
    public void addComment(Comment comment){
        this.comments.add(comment);
        ServerSaver.write(AllCommands.allData);
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(int views) throws Exception {
        if(views < 0)
            throw new Exception("views can't be negative!");
        this.views = views;
        ServerSaver.write(AllCommands.allData);
    }

    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public void addBuyer(Buyer buyer){
        buyers.add(buyer);
        ServerSaver.write(AllCommands.allData);
    }

    public void decreaseQuantity(int quantity) throws Exception {
        if(this.quantity > 0)
            this.quantity -= quantity;
        else
            throw new Exception("out of stock");
        ServerSaver.write(AllCommands.allData);
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
        ServerSaver.write(AllCommands.allData);
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
        ServerSaver.write(AllCommands.allData);
    }

    public void setBuyers(ArrayList<Buyer> buyers) {
        this.buyers = buyers;
        ServerSaver.write(AllCommands.allData);
    }

    public void setSpecialFeatures(HashMap<String, SpecialFeature> specialFeatures) {
        for (String specialFeature : category.getSpecialFeatures()) {
            specialFeatures.put(specialFeature,new SpecialFeature(""));
        }
        this.specialFeatures.putAll(specialFeatures);
        ServerSaver.write(AllCommands.allData);
    }
    public void removeProduct() {
        category.removeProduct(this);
        ServerSaver.write(AllCommands.allData);
    }
    public void removeSpecialFeature(String categorySpecialFeature){
        specialFeatures.put(categorySpecialFeature,new SpecialFeature(""));
    }



    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
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
                off == product.off &&
                views == product.views &&
                Objects.equals(productId, product.productId) &&
                Objects.equals(name, product.name) &&
                Objects.equals(companyName, product.companyName) &&
                Objects.equals(seller, product.seller) &&
                Objects.equals(situation,product.situation) &&
                Objects.equals(category, product.category) &&
                Objects.equals(specialFeatures, product.specialFeatures) &&
                Objects.equals(description, product.description) &&
                Objects.equals(scores, product.scores) &&
                Objects.equals(comments, product.comments) &&
                Objects.equals(buyers, product.buyers);
    }

}
