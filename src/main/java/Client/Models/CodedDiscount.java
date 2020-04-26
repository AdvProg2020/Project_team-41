package Client.Models;

import Client.Models.Person.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class CodedDiscount implements Serializable {
    public CodedDiscount(String discountCode, Date startDate, Date endDate, int[] amountOfDiscount, int discountRepeatsForEachUser, ArrayList<Person> people) {
        DiscountCode = discountCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountOfDiscount = amountOfDiscount;
        this.discountRepeatsForEachUser = discountRepeatsForEachUser;
        this.people = people;
    }

    private String DiscountCode;
    private Date startDate;
    private Date endDate;
    private int[] amountOfDiscount;// TODO: in the constructor : amountOfDiscount= new int[2];
    private int discountRepeatsForEachUser;
    private ArrayList<Person> people;

    public String getDiscountCode() {
        return DiscountCode;
    }

    public void setDiscountCode(String discountCode) {
        DiscountCode = discountCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int[] getAmountOfDiscount() {
        return amountOfDiscount;
    }

    public void setAmountOfDiscount(int[] amountOfDiscount) {
        this.amountOfDiscount = amountOfDiscount;
    }

    public int getDiscountRepeatsForEachUser() {
        return discountRepeatsForEachUser;
    }

    public void setDiscountRepeatsForEachUser(int discountRepeatsForEachUser) {
        this.discountRepeatsForEachUser = discountRepeatsForEachUser;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }
}
