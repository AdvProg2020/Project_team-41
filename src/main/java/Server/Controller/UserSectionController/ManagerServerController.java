package Server.Controller.UserSectionController;

import Client.Models.Category;
import Client.Models.CodedDiscount;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Product;
import Server.Database;
import ir.huri.jcal.JalaliCalendar;

import java.util.*;

public class ManagerServerController extends UserSectionServerController {
    private static ManagerServerController single_instance = null;
    public static ManagerServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new ManagerServerController();

        return single_instance;
    }

    private ManagerServerController(){
    }

    public Person getUserByUsername(String username){
        return Database.getPersonByUsername(username);
    }
    public void  deleteUser(String username) throws Exception {
            Database.deleteUser(username);
    }
    public void createManagerProfile(ArrayList<String> userInfo){
        Manager manager = new Manager();
        manager.setUserName(userInfo.get(0));
        manager.setPassword(userInfo.get(1));
        manager.setFirstName(userInfo.get(2));
        manager.setLastName(userInfo.get(3));
        manager.setEmail(userInfo.get(4));
        manager.setPhoneNumber(userInfo.get(5));
        manager.setCredit(Integer.parseInt(userInfo.get(6)));
        Database.addManager(manager);
        Database.addUser(manager);


    }
    public void  removeProduct(String productId){
        for (Category category : Database.getAllCategory()) {
            for (Product product : category.getProducts()) {
                if(product.getProductId().equals(productId)){
                    category.removeProduct(product);
                }
            }
        }
    }
    public void createDiscountCode(ArrayList<String> codeInformation){
        ArrayList<Person> people = new ArrayList<>();
        if((codeInformation.get(8).length() == 1) && (codeInformation.get(8).equalsIgnoreCase("allUsers"))){
            people.addAll(Database.getAllUsers());
        }
        else {
            for (String username : codeInformation.get(8).split(",")) {
                people.add(Database.getPersonByUsername(username));
            }
        }
        String[] dateTime = {codeInformation.get(1),codeInformation.get(2)};
        Date exactStartDate = getDateByDateTime(dateTime);
        dateTime = new String[]{codeInformation.get(3), codeInformation.get(4)};
        Date exactEndDate = getDateByDateTime(dateTime);

        Database.addDiscountCodes(new CodedDiscount(codeInformation.get(0),
                exactStartDate,exactEndDate,Integer.parseInt(codeInformation.get(5)),
                Integer.parseInt(codeInformation.get(6)),
                Integer.parseInt(codeInformation.get(7)),people));

    }
    public ArrayList<String> viewDiscountCode(String code){
        CodedDiscount codedDiscount = Database.getCodedDiscountByCode(code);
        ArrayList<String> discountCodeInformation = new ArrayList<>();


        discountCodeInformation.add("discount code: "+codedDiscount.getDiscountCode());
        discountCodeInformation.add("start date: "+convertGregorianToJalali(codedDiscount.getStartDate()).toString());
        discountCodeInformation.add("end date: "+convertGregorianToJalali(codedDiscount.getEndDate()).toString());
        discountCodeInformation.add("discount percentage: "+Integer.toString(codedDiscount.getDiscountPercentage()));
        discountCodeInformation.add("maximum discount: "+Integer.toString(codedDiscount.getMaximumDiscount()));
        discountCodeInformation.add("discount repeats for each user: "+Integer.toString(codedDiscount.getDiscountRepeatsForEachUser()));
        discountCodeInformation.add("people who can use it: ");
        for (Person person : codedDiscount.getPeople()) {
        discountCodeInformation.add(person.getUserName());
        }
        return discountCodeInformation;
    }
    public void  editDiscountCode(String code,HashMap<String,String> edits) {
        CodedDiscount codedDiscount = Database.getCodedDiscountByCode(code);
        for (String edit : edits.values()) {
            switch (edit){
                case "start date":{
                    String[] dateTime = edits.get("start date").split(",");
                    codedDiscount.setStartDate(getDateByDateTime(dateTime));
                }
                case "end date":{
                    String[] dateTime = edits.get("end date").split(",");
                    codedDiscount.setEndDate(getDateByDateTime(dateTime));
                }
                case "discount percentage":{
                    codedDiscount.setDiscountPercentage(Integer.parseInt(edits.get("discount percentage")));
                }
                case "maximum discount":{
                    codedDiscount.setMaximumDiscount(Integer.parseInt(edits.get("maximum discount")));
                }
                case "discount repeats for each user":{
                    codedDiscount.setDiscountRepeatsForEachUser(Integer.parseInt(edits.get("discount repeats for each user")));
                }
                case "people who can use it":{
                    ArrayList<Person> people = new ArrayList<>();
                    if((edits.get("people who can use it").length() == 1) && (edits.get("people who can use it").equalsIgnoreCase("allUsers"))){
                        people.addAll(Database.getAllUsers());
                    }
                    else {
                        for (String username : edits.get("people who can use it").split(",")) {
                            people.add(Database.getPersonByUsername(username));
                        }
                    }
                    codedDiscount.setPeople(people);
                }

            }

        }
    }
    public void  removeDiscountCode(String code){
        Database.deleteCodedDiscount(code);
    }
    public ArrayList<String> showRequest(){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public ArrayList<String> getRequestDetails(String request){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public void  acceptRequest(String request){
        System.err.println("fail");
    }
    public void declineRequest(String request){
        System.err.println("fail");
    }
    public ArrayList<String> showCategories(){
        ArrayList<String> categories = new ArrayList<>();
        for (Category category : Database.getAllCategory()) {
            categories.add(category.getName());
        }
        return categories;
    }
    public void editCategory(String category,String field,String editedField){
        System.err.println("fail");
    }
    public void addCategory(String categoryName,String specialFeatures){
        ArrayList<String> specialFeaturesArray = new ArrayList<>();
        Collections.addAll(specialFeaturesArray, specialFeatures.split(","));
        new Category(categoryName,specialFeaturesArray);
    }
    public void removeCategory(String categoryName) throws Exception {
        Database.deleteCategory(categoryName);
    }
    public ArrayList<String> viewAllDiscountCodes(){
        ArrayList<String> allDiscountCodes = new ArrayList<>();
        for (CodedDiscount discountCode : Database.getAllDiscountCodes()) {
            allDiscountCodes.add(discountCode.getDiscountCode());
        }
        return allDiscountCodes;
    }
    public JalaliCalendar convertGregorianToJalali(Date date){
        JalaliCalendar jalaliCalendar = new JalaliCalendar();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        jalaliCalendar.fromGregorian(gregorianCalendar);
        return jalaliCalendar;
    }
    public Date convertJalaliToGregorian(String year, String month, String day, String hour, String minute, String second){

        JalaliCalendar jalaliCalendar = new JalaliCalendar();
        Date date = new Date();
        jalaliCalendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
        date.setTime(jalaliCalendar.toGregorian().getTimeInMillis()+1000*((Integer.parseInt(hour)*60*60)+Integer.parseInt(minute)*60+Integer.parseInt(second)));
        return date;
    }
    public Date getDateByDateTime(String[] dateTime){
        String givenDate;
        String givenTime;
        String[] dayMonthYear;
        String[] hourMinuteSecond;
        givenDate = dateTime[0];
        givenTime = dateTime[1];
        dayMonthYear = givenDate.split("/");
        hourMinuteSecond = givenTime.split(":");
        Date exactDate = convertJalaliToGregorian(dayMonthYear[0],dayMonthYear[1],dayMonthYear[2],hourMinuteSecond[0],hourMinuteSecond[1],hourMinuteSecond[2]);
        return exactDate;
    }
}
