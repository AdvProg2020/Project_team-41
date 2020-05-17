package Server.Controller.UserSectionController;

import Client.Models.*;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Server.Controller.TimeControl;
import Server.Database;

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
    public void editCategorySpecialFeatures(String category,String editedField) throws Exception {
        ArrayList<String> specialFeatures = new ArrayList<>();
        Collections.addAll(specialFeatures, editedField.split(","));
        Database.getCategoryByName(category).setSpecialFeatures(specialFeatures);
    }
    public ArrayList<Product> getAllProducts(){
        return Database.getAllProducts();
    }

    public ArrayList<String> getAllUsers(){
        ArrayList<String> allUsers = new ArrayList<>();
        for (Person user : Database.getAllUsers()) {
            allUsers.add(user.getUserName());
        }
        return allUsers;
    }

    public Person getUserByUsername(String username) throws Exception {
        return Database.getPersonByUsername(username);
    }
    public void  deleteUser(String username) throws Exception {
            Database.deleteUser(username);
    }
    public void createManagerProfile(ArrayList<String> userInfo) throws Exception {
        Manager manager = new Manager();
        manager.setUserName(userInfo.get(0));
        manager.setPassword(userInfo.get(1));
        manager.setFirstName(userInfo.get(2));
        manager.setLastName(userInfo.get(3));
        manager.setEmail(userInfo.get(4));
        manager.setPhoneNumber(userInfo.get(5));
        manager.setCredit(Integer.parseInt(userInfo.get(6)));
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
    public void createDiscountCode(ArrayList<String> codeInformation) throws Exception {
        ArrayList<Person> people = new ArrayList<>();
        if((codeInformation.get(8).split(",").length == 1) && (codeInformation.get(8).equalsIgnoreCase("allUsers"))){
            people.addAll(Database.getAllUsers());
        }
        else {
            for (String username : codeInformation.get(8).split(",")) {
                people.add(Database.getPersonByUsername(username));
            }
        }
        String[] dateTime = {codeInformation.get(1),codeInformation.get(2)};
        //todo check if this code does not exists
        Date exactStartDate = TimeControl.getDateByDateTime(dateTime);
        dateTime = new String[]{codeInformation.get(3), codeInformation.get(4)};
        Date exactEndDate = TimeControl.getDateByDateTime(dateTime);

        Database.addDiscountCodes(new CodedDiscount(codeInformation.get(0),
                exactStartDate,exactEndDate,Integer.parseInt(codeInformation.get(5)),
                Integer.parseInt(codeInformation.get(6)),
                Integer.parseInt(codeInformation.get(7)),people));

    }
    public ArrayList<String> viewDiscountCode(String code) throws Exception {
        CodedDiscount codedDiscount = Database.getCodedDiscountByCode(code);
        ArrayList<String> discountCodeInformation = new ArrayList<>();


        discountCodeInformation.add("discount code: "+codedDiscount.getDiscountCode());
        discountCodeInformation.add("start date: "+TimeControl.getJalaliDateAndTimeForPrint(codedDiscount.getStartDate()));
        discountCodeInformation.add("end date: "+TimeControl.getJalaliDateAndTimeForPrint(codedDiscount.getEndDate()));
        discountCodeInformation.add("discount percentage: "+ codedDiscount.getDiscountPercentage());
        discountCodeInformation.add("maximum discount: "+ codedDiscount.getMaximumDiscount());
        discountCodeInformation.add("discount repeats for each user: "+ codedDiscount.getDiscountRepeatsForEachUser());
        discountCodeInformation.add("people who can use it: ");
        for (Person person : codedDiscount.getPeople().keySet()) {
        discountCodeInformation.add(person.getUserName());
        }
        return discountCodeInformation;
    }
    public void  editDiscountCode(String code,HashMap<String,String> edits) throws Exception {
        CodedDiscount codedDiscount = Database.getCodedDiscountByCode(code);
        for (String edit : edits.keySet()) {
            switch (edit){
                case "start date":{
                    String[] dateTime = edits.get("start date").split(",");
                    codedDiscount.setStartDate(TimeControl.getDateByDateTime(dateTime));
                    break;
                }
                case "end date":{
                    String[] dateTime = edits.get("end date").split(",");
                    codedDiscount.setEndDate(TimeControl.getDateByDateTime(dateTime));
                    break;
                }
                case "discount percentage":{
                    codedDiscount.setDiscountPercentage(Integer.parseInt(edits.get("discount percentage")));
                    break;
                }
                case "maximum discount":{
                    codedDiscount.setMaximumDiscount(Integer.parseInt(edits.get("maximum discount")));
                    break;
                }
                case "discount repeats for each user":{
                    codedDiscount.setDiscountRepeatsForEachUser(Integer.parseInt(edits.get("discount repeats for each user")));
                    break;
                }
                case "people who can use it":{
                    HashMap<Person,Integer> people = new HashMap<>();
                    if((edits.get("people who can use it").length() == 1) && (edits.get("people who can use it").equalsIgnoreCase("allUsers"))){
                        for (Person user : Database.getAllUsers()) {
                            people.put(user,codedDiscount.getDiscountRepeatsForEachUser());
                        }
                    }
                    else {
                        for (String username : edits.get("people who can use it").split(",")) {
                            people.put(Database.getPersonByUsername(username),codedDiscount.getDiscountRepeatsForEachUser());
                        }
                    }
                    codedDiscount.setPeople(people);
                    break;
                }
                default:{
                    throw new Exception("wrong field");
                }

            }

        }
    }
    public void  removeDiscountCode(String code) throws Exception {
        Database.deleteCodedDiscount(code);
    }
    public ArrayList<String> showRequest(){
        ArrayList<String> requests = new ArrayList<>();
        for (Request request : Database.getAllRequest()) {
            requests.add(request.getRequestId() + " : " + request.getRequestType());
        }
        return requests;
    }
    public ArrayList<String> getRequestDetails(String requestId) throws Exception {
        ArrayList<String> requestDetails = new ArrayList<>();
        Request request = Database.getRequestByRequestId(requestId);
        switch (request.getRequestType()){
            case "ADD_COMMENT" :{
                requestDetails.add("comment details:");
                requestDetails.add(request.getComment().toString());
                break;
            }
            case "ADD_PRODUCT" :
            case "REMOVE_PRODUCT" :
                {
                    requestDetails.add("product details:");
                    requestDetails.addAll(getProductDetails(request.getProduct()));
                    break;
                 }
            case "EDIT_PRODUCT" :{
                requestDetails.add("product details:");
                requestDetails.addAll(getProductDetails(request.getProduct()));
                requestDetails.add("original product:");
                requestDetails.add(request.getProduct().toString());
                requestDetails.add("edited product:");
                requestDetails.add(request.getEditedProduct().toString());

                break;
            }
            case "ADD_OFF" :{
                    requestDetails.add("off details:");
                    requestDetails.addAll(getOffDetails(request.getOff()));
                    break;
            }
            case "EDIT_OFF" : {
                    requestDetails.add("off details:");
                    requestDetails.addAll(getOffDetails(request.getOff()));
                    requestDetails.add("original off:");
                    requestDetails.add(request.getOff().toString());
                    requestDetails.add("edited off:");
                    requestDetails.add(request.getEditedOff().toString());

                break;
            }

            case "REGISTER_SELLER" :{
                requestDetails.add("seller details:");
                requestDetails.addAll(getSellerDetails(request.getOff().getSeller()));
                break;
            }
        }

        return requestDetails;
    }
    public void  acceptRequest(String requestId) throws Exception {
        Request request = Database.getRequestByRequestId(requestId);
        switch (request.getRequestType()){
            case "ADD_COMMENT" :{
                Comment comment = request.getComment();
                comment.getProduct().addComment(comment);
                comment.setCommentSituation(CommentSituation.CONFIRMED);
                break;
            }
            case "ADD_PRODUCT" :{
                for (Product otherProduct : Database.getAllProducts()) {
                    if(otherProduct.getName().equals(request.getProduct().getName()))
                        throw new Exception("name is already chosen for another product");
                }
                Database.addProduct(request.getProduct());
                request.getSeller().addProduct(request.getProduct());
                break;
            }
            case "REMOVE_PRODUCT" : {
                Database.removeProduct(request.getProduct());
                request.getSeller().removeProduct(request.getProduct());
                break;
            }
            case "EDIT_PRODUCT" :{

                Database.getAllProducts().remove(request.getProduct());
                Database.addProduct(request.getEditedProduct());
                request.getSeller().removeProduct(request.getProduct());
                request.getSeller().addProduct(request.getEditedProduct());

                break;
            }
            case "ADD_OFF" :{
                for (Product product : request.getOff().getProducts()) {
                    product.setIsItInOff(true);
                }
                Database.addOff(request.getOff());
                request.getSeller().addOff(request.getOff());
                break;
            }
            case "EDIT_OFF" : {
                for (Product product : request.getOff().getProducts()) {
                    product.setIsItInOff(false);
                }
                for (Product product : request.getEditedOff().getProducts()) {
                    product.setIsItInOff(true);
                }

                Database.getAllOffs().remove(request.getOff());
                Database.addOff(request.getEditedOff());
                request.getSeller().removeOff(request.getOff());
                request.getSeller().addOff(request.getEditedOff());
                break;
            }
            //todo add remove off and remember to set every product isItInOff to false

            case "REGISTER_SELLER" :{
                Database.addUser(request.getSeller());
                break;
            }
        }
        Database.removeRequest(request);

    }
    public void declineRequest(String requestId) throws Exception {
        Request request = Database.getRequestByRequestId(requestId);
        Database.removeRequest(request);
    }
    public ArrayList<String> showCategories(){
        ArrayList<String> categories = new ArrayList<>();
        for (Category category : Database.getAllCategory()) {
            categories.add(category.getName());
        }
        return categories;
    }
    public void editCategoryName(String category, String editedField) throws Exception {
        Database.getCategoryByName(category).setName(editedField);
    }
    public void addCategory(String categoryName,String specialFeatures) throws Exception {
        Category category;
        try {
            Database.getCategoryByName(categoryName);
        }
        catch (Exception e) {
            if(e.getMessage().equals("No category found with this name")) {
                ArrayList<String> specialFeaturesArray = new ArrayList<>();
                Collections.addAll(specialFeaturesArray, specialFeatures.split(","));
                category = new Category(categoryName, specialFeaturesArray);
                Database.addCategory(category);
                return;
            }
            else{
                throw new Exception("unknown problem occurred while creating category");
            }
        }
            throw new Exception("category exists with this name");

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

    public ArrayList<String> getSellerDetails(Seller seller){
        ArrayList<String> sellerDetails  = new ArrayList<>();
        sellerDetails.add("username : "+seller.getUserName());
        sellerDetails.add("first name : "+seller.getFirstName());
        sellerDetails.add("last name : "+seller.getLastName());
        sellerDetails.add("email : "+seller.getEmail());
        sellerDetails.add("phone number : "+seller.getPhoneNumber());
        return sellerDetails;
    }
    public  ArrayList<String> getProductDetails(Product product){
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
    public  ArrayList<String> getOffDetails(Off off){
        ArrayList<String> offDetails  = new ArrayList<>();
        offDetails.add("off id : " + off.getOffId());
        offDetails.add("amount of discount : " + off.getAmountOfDiscount());
        offDetails.add("start date : " + TimeControl.getJalaliDateAndTimeForPrint(off.getStartDate()));
        offDetails.add("end date : " + TimeControl.getJalaliDateAndTimeForPrint(off.getEndDate()));
        offDetails.add("products : ");
        for (Product product : off.getProducts()) {
            offDetails.add(product.getName());
        }
        return offDetails;
    }
}
