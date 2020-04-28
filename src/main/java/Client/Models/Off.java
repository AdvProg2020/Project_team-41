package Client.Models;

import Client.Models.Person.Seller;

import java.util.ArrayList;
import java.util.Date;

public class Off {
    private String offId;
    private ArrayList<Product>products;
    private Situation situation;
    private Date startDate;
    private Date endDate;
    private int amountOfDiscount;
    private Seller seller;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
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

}
