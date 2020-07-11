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
}
