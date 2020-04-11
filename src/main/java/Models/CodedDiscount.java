package Models;

import Models.Person.Person;

import java.util.ArrayList;
import java.util.Date;

public class CodedDiscount {
    private String DiscountCode;
    private Date startDate;
    private Date endDate;
    private int[] amountOfDiscount;// TODO: in the constructor : amountOfDiscount= new int[2];
    private int discountRepeatsForEachUser;
    private ArrayList<Person> people;
}
