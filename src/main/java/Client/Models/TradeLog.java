package Client.Models;

import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

public class TradeLog implements Serializable {
    private String logId;
    private Date date;
    private int money;
    private int offAmount; //if existed
    private HashMap<Product,Integer> items;
    private String buyerName;
    private String deliverySituation;
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    //for generating token


    public TradeLog(Date date, int money, int offAmount, HashMap<Product,Integer> items, String buyerName, String deliverySituation) {
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
        ServerSaver.write(AllCommands.allData);
    }

    public int getMoney() {
        return money;
    }

    public int getOffAmount() {
        return offAmount;
    }

    public HashMap<Product, Integer> getItems() {
        return items;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getDeliverySituation() {
        return deliverySituation;
    }

    public static String generateNewToken() {
        byte[] randomBytes = new byte[2];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    @Override
    public String toString() {
        StringBuilder products = new StringBuilder();
        for (Product item : items.keySet()) {
            products.append("name :").append(item.getName()).append(" - quantity :").append(items.get(item));
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
