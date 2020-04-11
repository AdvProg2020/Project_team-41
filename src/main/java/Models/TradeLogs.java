package Models;

import Models.Product;

import java.util.ArrayList;
import java.util.Date;

public class TradeLogs {
    private String logId;
    private Date date;
    private int money;
    private int offAmount; //if existed
    private ArrayList<Product> items;
    private String buyerName;
    private String deliverySituation;


}
