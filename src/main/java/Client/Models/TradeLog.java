package Client.Models;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class TradeLog implements Serializable {
    private String logId;
    private Date date;
    private int money;
    private int offAmount; //if existed
    private ArrayList<Product> items;
    private String buyerName;
    private String deliverySituation;
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    //for generating token


    public TradeLog(Date date, int money, int offAmount, ArrayList<Product> items, String buyerName, String deliverySituation) {
        this.logId = generateNewToken();
        this.date = date;
        this.money = money;
        this.offAmount = offAmount;
        this.items = items;
        this.buyerName = buyerName;
        this.deliverySituation = deliverySituation;
    }

    public String getLogId() {
        return logId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getOffAmount() {
        return offAmount;
    }

    public void setOffAmount(int offAmount) {
        this.offAmount = offAmount;
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public void setItems(ArrayList<Product> items) {
        this.items = items;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getDeliverySituation() {
        return deliverySituation;
    }

    public void setDeliverySituation(String deliverySituation) {
        this.deliverySituation = deliverySituation;
    }
    public static String generateNewToken() {
        byte[] randomBytes = new byte[4];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    @Override
    public String toString() {
        StringBuilder products = new StringBuilder();
        for (Product item : items) {
            products.append(item.getName()+ "\n");
        }

        return "TradeLog{" +
                "logId='" + logId + '\'' +
                ", date=" + date +
                ", money=" + money +
                ", offAmount=" + offAmount +
                ", items=" + products +
                ", deliverySituation='" + deliverySituation + '\'' +
                '}';
    }
}
