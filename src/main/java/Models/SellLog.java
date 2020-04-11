package Models;

import java.util.ArrayList;
import java.util.Date;

public class SellLog {
    private String logCode;
    private Date date;
    private int money;
    private int offAmount; //if existed
    private ArrayList<Product> soldItems;
    private String buyerName;
    private String deliverySituation;


}
