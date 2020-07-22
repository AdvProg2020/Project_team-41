package Client.Models;

import Server.Controller.AllCommands;
import Server.Controller.RandomNumberGenerator;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class TradeLog implements Serializable {
    private final String logId;
    private Date date;
    private final int money;
    private final int offAmount; //if existed
    private final HashMap<Product,Integer> items;
    private final String buyerName;
    private DeliverySituation deliverySituation;
    private final ArrayList<String> information;


    public TradeLog(Date date, int money, int offAmount, HashMap<Product,Integer> items, String buyerName, DeliverySituation deliverySituation, ArrayList<String> information) {
        this.logId = RandomNumberGenerator.getToken(5);
        this.date = date;
        this.money = money;
        this.offAmount = offAmount;
        this.items = items;
        this.buyerName = buyerName;
        this.deliverySituation = deliverySituation;
        this.information = information;
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

    public int getOffAmount() {
        return offAmount;
    }

    public HashMap<Product, Integer> getItems() {
        return items;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public DeliverySituation getDeliverySituation() {
        return deliverySituation;
    }

    public void setDeliverySituation(DeliverySituation deliverySituation) {
        this.deliverySituation = deliverySituation;
    }

    public ArrayList<String> getInformation() {
        return information;
    }

    @Override
    public String toString() {
        StringBuilder products = new StringBuilder();
        for (Product item : items.keySet()) {
            products.append("id :").append(item.getProductId()).append(" - name :").append(item.getName()).append(" - quantity :").append(items.get(item));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeLog tradeLog = (TradeLog) o;
        return money == tradeLog.money &&
                offAmount == tradeLog.offAmount &&
                Objects.equals(logId, tradeLog.logId) &&
                Objects.equals(date, tradeLog.date) &&
                Objects.equals(items, tradeLog.items) &&
                Objects.equals(buyerName, tradeLog.buyerName) &&
                Objects.equals(deliverySituation, tradeLog.deliverySituation);
    }
    public enum DeliverySituation{
        WAITING, SENT
    }

}
