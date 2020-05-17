package Client.Models;

import Client.Models.Person.Seller;
import Server.Controller.TimeControl;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class Off implements Serializable {
    private String offId;
    private ArrayList<Product>products;
    private Situation situation;
    private Date startDate;
    private Date endDate;
    private int amountOfDiscount;
    private Seller seller;
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    //for generating token

    public Off(ArrayList<Product> products, Situation situation, Date startDate, Date endDate, int amountOfDiscount, Seller seller) throws Exception {
        this.offId = generateNewToken();
        this.products = products;
        this.situation = situation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.setAmountOfDiscount(amountOfDiscount);
        this.seller = seller;
    }

    public Off cloneOff() throws Exception{
            Off off =  new Off((ArrayList<Product>) products.clone(),situation,(Date) startDate.clone(),(Date) endDate.clone(),amountOfDiscount,seller);
            off.setOffId(offId);
            return off;
    }

    public void setOffId(String offId) {
        this.offId = offId;
    }

    public boolean hasProduct(Product product){
        for (Product offProduct : getProducts()) {
            if(offProduct.equals(product))
                return true;
        }
        return false;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setAmountOfDiscount(int amountOfDiscount) throws Exception {
        if(amountOfDiscount < 0)
            throw new Exception("amount fo discount should be positive");
        this.amountOfDiscount = amountOfDiscount;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getOffId() {
        return offId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getAmountOfDiscount() {
        return amountOfDiscount;
    }

    public static String generateNewToken() {
        byte[] randomBytes = new byte[2];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    public void deleteOff(){
        for (Product product : products) {
            product.setOff(null);
            seller.removeOff(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder products = new StringBuilder();
        for (Product item : this.products) {
            products.append(item.getName()).append("\n");
        }
        return
                "offId : '" + offId + '\'' +
                ", situation : " + situation +
                ", startDate : " + TimeControl.getJalaliDateAndTimeForPrint(startDate) +
                ", endDate : " + TimeControl.getJalaliDateAndTimeForPrint(endDate) +
                ", amountOfDiscount : " + amountOfDiscount +
                ", seller : " + seller.getUserName() +
                ", products : {" + products +
                '}';
    }
}
