package Client.Models;

import Client.Models.Person.Seller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class Off {
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

    public Off(ArrayList<Product> products, Situation situation, Date startDate, Date endDate, int amountOfDiscount, Seller seller) {

        this.offId = generateNewToken();
        this.products = products;
        this.situation = situation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountOfDiscount = amountOfDiscount;
        this.seller = seller;
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

    public void setAmountOfDiscount(int amountOfDiscount) {
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
        byte[] randomBytes = new byte[4];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }


}
