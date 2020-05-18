package Client.Models;

import Client.Models.Person.Seller;
import Server.Controller.AllCommands;
import Server.Controller.RandomNumberGenerator;
import Server.Controller.ServerSaver;
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

    public Off(ArrayList<Product> products, Situation situation, Date startDate, Date endDate, int amountOfDiscount, Seller seller) throws Exception {
        this.offId = RandomNumberGenerator.getToken(5);
        this.products = products;
        this.situation = situation;
        this.setStartDate(startDate);
        this.setEndDate(endDate);
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
        ServerSaver.write(AllCommands.allData);
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
        ServerSaver.write(AllCommands.allData);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        ServerSaver.write(AllCommands.allData);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        ServerSaver.write(AllCommands.allData);
    }

    public void setAmountOfDiscount(int amountOfDiscount) throws Exception {
        if(amountOfDiscount < 0)
            throw new Exception("amount fo discount should be positive");
        this.amountOfDiscount = amountOfDiscount;
        ServerSaver.write(AllCommands.allData);
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


    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        ServerSaver.write(AllCommands.allData);
    }
    public void deleteOff(){
        for (Product product : products) {
            product.setOff(null);
            seller.removeOff(this);
        }
        ServerSaver.write(AllCommands.allData);
    }
    public void removeProduct(Product product){
        products.remove(product);
        ServerSaver.write(AllCommands.allData);
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
