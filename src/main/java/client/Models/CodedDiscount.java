package Client.Models;

import Client.Models.Person.Person;
import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class CodedDiscount implements Serializable {
    public CodedDiscount(String discountCode, Date startDate, Date endDate, int discountPercentage,int maximumDiscount, int discountRepeatsForEachUser, ArrayList<Person> people) throws Exception {
        this.setDiscountCode(discountCode);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setDiscountPercentage(discountPercentage);
        this.setMaximumDiscount(maximumDiscount);
        this.setDiscountRepeatsForEachUser(discountRepeatsForEachUser);
        this.people = new HashMap<>();
        for (Person person : people) {
            this.people.put(person,discountRepeatsForEachUser);
        }
    }

    private String discountCode;
    private Date startDate;
    private Date endDate;
    private int discountPercentage;
    private int maximumDiscount;
    private int discountRepeatsForEachUser;
    private HashMap<Person,Integer> people;

    public void setDiscountCode(String discountCode) throws Exception {
        if(discountCode.isBlank())
            throw new Exception("discount code can't be blank!");
        this.discountCode = discountCode;
    }

    public String getDiscountCode() {
        return discountCode;
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
    public void reduceDiscountCodeForUser(Person person){
        int codesLeft = people.get(person)-1;
        if (codesLeft == 0)
            people.remove(person);
        else
            people.put(person,codesLeft);

    }
    public int getDiscountCodeRemainingForUser(Person person){
        return people.get(person);
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
    public int howMuchWillItCost(int price){
        int discountAmount = (price*discountPercentage)/100;
        return price - Math.min((discountAmount), maximumDiscount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodedDiscount that = (CodedDiscount) o;
        return discountPercentage == that.discountPercentage &&
                maximumDiscount == that.maximumDiscount &&
                discountRepeatsForEachUser == that.discountRepeatsForEachUser &&
                Objects.equals(discountCode, that.discountCode) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(people, that.people);
    }

}
