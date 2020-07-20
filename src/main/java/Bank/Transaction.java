package Bank;

import Server.Controller.RandomNumberGenerator;
import com.google.gson.Gson;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String receiptType;
    private int money;
    private int sourceAccountID;
    private int destAccountID;
    private String description;
    private int id;
    private int paid;

    public static String getString(Transaction transaction){
        Gson gson=new Gson();
        return gson.toJson(transaction,Transaction.class);
    }


    public int getId() {
        return id;
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

    public int getPaid() {
        return paid;
    }

    public Transaction(String receiptType, int money, int sourceAccountID, int destAccountID, String description) {
        this.receiptType = receiptType;
        this.money = money;
        this.sourceAccountID = sourceAccountID;
        this.destAccountID = destAccountID;
        this.description = description;
        this.id= Integer.parseInt(RandomNumberGenerator.getIdNumber(5));
        this.paid=0;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }
}
