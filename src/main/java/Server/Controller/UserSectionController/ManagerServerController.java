package Server.Controller.UserSectionController;

import Client.Models.*;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
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
        ArrayList<String> requests = new ArrayList<>();
        for (Request request : Database.getAllRequest()) {
            requests.add(request.getRequestId() + " : " + request.getRequestType());
        }
        return requests;
    }
    public ArrayList<String> getRequestDetails(String requestId){
        ArrayList<String> requestDetails = new ArrayList<>();
        Request request = Database.getRequestByRequestId(requestId);
        switch (request.getRequestType()){
            case "ADD_PRODUCT" :
            case "REMOVE_PRODUCT" :
                {
                    requestDetails.add("product details:");
                    requestDetails.addAll(getProductDetails(request.getProduct()));
                 }
            case "EDIT_PRODUCT" :{
                requestDetails.add("product details:");
                requestDetails.addAll(getProductDetails(request.getProduct()));
                requestDetails.add("product edits:");
                for (String editRequestKey : request.getEditRequest().keySet()) {
                    requestDetails.add(editRequestKey + "-" + request.getEditRequest().get(editRequestKey));
                }
            }
            case "ADD_OFF" :{
                    requestDetails.add("off details:");
                    requestDetails.addAll(getOffDetails(request.getOff()));
            }
            case "EDIT_OFF" : {
                    requestDetails.add("off details:");
                    requestDetails.addAll(getOffDetails(request.getOff()));
                    requestDetails.add("off edits:");
                for (String editRequestKey : request.getEditRequest().keySet()) {
                    requestDetails.add(editRequestKey + "-" + request.getEditRequest().get(editRequestKey));
                }
            }

            case "REGISTER_SELLER" :{
                requestDetails.add("seller details:");
                requestDetails.addAll(getSellerDetails(request.getOff().getSeller()));
            }
        }

        return requestDetails;
    }
    public void  acceptRequest(String requestId){
        Request request = Database.getRequestByRequestId(requestId);
        switch (request.getRequestType()){
            case "ADD_PRODUCT" :{
                Database.addProduct(request.getProduct());
            }
            case "REMOVE_PRODUCT" : {
                Database.removeProduct(request.getProduct());
            }
            case "EDIT_PRODUCT" :{
                Product product = request.getProduct();
                for (String editRequestKey : request.getEditRequest().keySet()) {
                    String editRequestValue = request.getEditRequest().get(editRequestKey);
                    switch (editRequestKey){
                        case "seller" :{
                            product.setSeller(Database.getSellerByUsername(editRequestValue));
                        }
                        case "price" :{
                            product.setPrice(Integer.parseInt(editRequestValue));
                        }
                        case "companyName" :{
                            product.setCompanyName(editRequestValue);
                        }
                        case "description" :{
                            product.setDescription(editRequestValue);
                        }
                        case "name" :{
                            product.setName(editRequestValue);
                        }
                    }
                }
            }
            case "ADD_OFF" :{
                Database.addOff(request.getOff());
            }
            case "EDIT_OFF" : {
                Off off = request.getOff();
                for (String editRequestKey : request.getEditRequest().keySet()) {
                    String editRequestValue = request.getEditRequest().get(editRequestKey);
                    switch (editRequestKey){
                        case "startDate" :{
                            off.setStartDate(getDateByDateTime(editRequestValue.split(",")));
                        }
                        case "endDate" :{
                            off.setEndDate(getDateByDateTime(editRequestValue.split(",")));
                        }
                        case "amountOfDiscount" :{
                            off.setAmountOfDiscount(Integer.parseInt(editRequestValue));
                        }
                    }
                }
            }

            case "REGISTER_SELLER" :{
                Database.addUser(request.getSeller());
            }
        }

    }
    public void declineRequest(String requestId){
        Request request = Database.getRequestByRequestId(requestId);
    }
    public ArrayList<String> showCategories(){
        ArrayList<String> categories = new ArrayList<>();
        for (Category category : Database.getAllCategory()) {
            categories.add(category.getName());
        }
        return categories;
    }
    public void editCategory(String category,String field,String editedField) throws Exception {
        Database.getCategoryByName(category).setName(editedField);
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
    public ArrayList<String> getProductDetails(Product product){
        ArrayList<String> productDetails = new ArrayList<>();
        productDetails.add("Name : " + product.getName());
        productDetails.add("Description : " + product.getDescription());
        productDetails.add("Company name : " + product.getCompanyName());
        productDetails.add("Price : " + product.getPrice());
        productDetails.add("Product id : " + product.getProductId());
        productDetails.add("Category : " + product.getCategory().getName());
        productDetails.add("Seller's Username : " + product.getSeller().getUserName());
        productDetails.add("Special Features : ");
        for (String specialFeatureKey : product.getSpecialFeatures().keySet()) {
            productDetails.add(specialFeatureKey + " - " + product.getSpecialFeatures().get(specialFeatureKey));
        }
        return productDetails;
    }
    public ArrayList<String> getOffDetails(Off off){
        ArrayList<String> offDetails  = new ArrayList<>();
        offDetails.add("off id : " + off.getOffId());
        offDetails.add("amount of discount : " + off.getAmountOfDiscount());
        offDetails.add("start date : " + convertGregorianToJalali(off.getStartDate()));
        offDetails.add("end date : " + convertGregorianToJalali(off.getEndDate()));
        offDetails.add("products : ");
        for (Product product : off.getProducts()) {
            offDetails.add(product.getName());
        }
        return offDetails;
    }
    public ArrayList<String> getSellerDetails(Seller seller){
        ArrayList<String> sellerDetails  = new ArrayList<>();
        sellerDetails.add("username : "+seller.getUserName());
        sellerDetails.add("first name : "+seller.getFirstName());
        sellerDetails.add("last name : "+seller.getLastName());
        sellerDetails.add("email : "+seller.getEmail());
        sellerDetails.add("phone number : "+seller.getPhoneNumber());
        return sellerDetails;
    }
}
