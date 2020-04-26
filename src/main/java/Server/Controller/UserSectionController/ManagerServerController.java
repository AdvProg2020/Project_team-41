package Server.Controller.UserSectionController;

import Client.Models.Category;
import Client.Models.CodedDiscount;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Product;
import Server.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
        for (Person user : Database.getAllUsers()) {
            if(user.getUserName().equals(username)){
                return user;
            }
        }
        return null;
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

    }
    public ArrayList<String> viewDiscountCode(String code){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public void  editDiscountCode(String code,HashMap<String,String> edits) {
    }
    public void  removeDiscountCode(String code){

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

    }
    public void declineRequest(String request){

    }
    public ArrayList<String> showCategories(){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public void editCategory(String category,String field,String editedField){

    }
    public void addCategory(String categoryName,String specialFeatures){
        ArrayList<String> specialFeaturesArray = new ArrayList<>();
        ArrayList<Product> Products = new ArrayList<>();
        Collections.addAll(specialFeaturesArray, specialFeatures.split(","));

        Database.addCategory(new Category(categoryName,specialFeaturesArray));
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


}
