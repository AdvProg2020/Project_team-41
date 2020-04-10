package Models;

import java.util.ArrayList;
import java.util.Date;

public class DiscountCode {
    private String DiscountCodeCode;
    private Date startDate;
    private Date endDate;
    private int amount;
    private int timeForEachUser;
    private ArrayList<Person> people;
}
