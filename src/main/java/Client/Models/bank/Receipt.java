package Client.Models.bank;

import com.google.gson.Gson;

public class Receipt {


    private String receiptType;
    private int money;
    private int sourceAccountID;
    private int destAccountID;
    private String description;
    private int id;
    private int paid;

    public Receipt(String receiptType, int money, int sourceAccountID, int destAccountID, String description, int id, int paid) {
        this.receiptType = receiptType;
        this.money = money;
        this.sourceAccountID = sourceAccountID;
        this.destAccountID = destAccountID;
        this.description = description;
        this.id = id;
        this.paid = paid;
    }
    public Receipt(){

    }

    public static Receipt getFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Receipt.class);
    }

    public String getReceiptType() {
        return receiptType;
    }

    public int getMoney() {
        return money;
    }

    public int getSourceAccountID() {
        return sourceAccountID;
    }

    public int getDestAccountID() {
        return destAccountID;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public int getPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptType='" + receiptType + '\'' +
                ", money=" + money +
                ", sourceAccountID=" + sourceAccountID +
                ", destAccountID=" + destAccountID +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", paid=" + paid +
                '}';
    }
}
