package Client.Models;

import Client.Models.Person.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CodedDiscount implements Serializable {
    public CodedDiscount(String discountCode, Date startDate, Date endDate, int discountPercentage,int maximumDiscount, int discountRepeatsForEachUser, ArrayList<Person> people) {
        DiscountCode = discountCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercentage = discountPercentage;
        this.maximumDiscount = maximumDiscount;
        this.discountRepeatsForEachUser = discountRepeatsForEachUser;
        for (Person person : people) {
            this.people.put(person,discountRepeatsForEachUser);
        }
    }

    private String DiscountCode;
    private Date startDate;
    private Date endDate;
    private int discountPercentage;
    private int maximumDiscount;
    private int discountRepeatsForEachUser;
    private HashMap<Person,Integer> people;

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

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) throws Exception {
        if((discountPercentage<0) || (discountPercentage>100))
            throw new Exception("percentage should be between 0 and 100");
        this.discountPercentage = discountPercentage;
    }

    public int getMaximumDiscount() {
        return maximumDiscount;
    }

    public void setMaximumDiscount(int maximumDiscount) throws Exception {
        if(maximumDiscount<1)
            throw new Exception("maximum discount should be positive :/ ");
        this.maximumDiscount = maximumDiscount;
    }

    public int getDiscountRepeatsForEachUser() {
        return discountRepeatsForEachUser;
    }

    public void setDiscountRepeatsForEachUser(int discountRepeatsForEachUser) throws Exception {
        if(discountRepeatsForEachUser<1)
            throw new Exception("discount repeats should be positive :/ ");
        this.discountRepeatsForEachUser = discountRepeatsForEachUser;
    }

    public HashMap<Person, Integer> getPeople() {
        return people;
    }

    public void setPeople(HashMap<Person, Integer> people) {
        this.people = people;
    }
    public boolean hasPerson(Person person){
        return people.get(person) != null;
    }
}
