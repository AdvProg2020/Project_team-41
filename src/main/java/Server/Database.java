package Server;

import Client.Models.*;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;

import java.io.Serializable;
import java.util.ArrayList;


public class Database implements Serializable {
    private static ArrayList<Category> allCategory=new ArrayList<>();
    private static ArrayList<Request> allRequest=new ArrayList<>();
    private static ArrayList<Person> allUsers=new ArrayList<>();
    private static ArrayList<Manager>allManagers=new ArrayList<>();
    private static ArrayList<CodedDiscount> allDiscountCodes=new ArrayList<>();
    private static ArrayList<Product> allProducts=new ArrayList<>();
    private static ArrayList<Seller> allSellers=new ArrayList<>();
    private static ArrayList<Off>allOffs=new ArrayList<>();

    public static ArrayList<Off> getAllOffs() {
        return allOffs;
    }
//todo move all here

    public static Category getCategoryByName(String name) throws Exception {
        for (Category category : allCategory) {
            if(category.getName().equals(name)){
                return category;
            }
        }
        return null;
    }
    public static Person getPersonByUsername(String username){
        for (Person user : allUsers) {
            if(user.getUserName().equalsIgnoreCase(username))
                return user;
        }
        return null;
    }
    public static CodedDiscount getCodedDiscountByCode(String code){
        for (CodedDiscount codedDiscount : allDiscountCodes) {
            if(codedDiscount.equals(code))
                return codedDiscount;
        }
        return null;
    }

    public static Seller getSellerByName(String name) throws Exception {
        for (Seller seller : allSellers) {
            if(seller.getUserName().equals(name)){
                return seller;
            }
        }
        throw new Exception("no seller found");
    }

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public static ArrayList<CodedDiscount> getAllDiscountCodes() {
        return allDiscountCodes;
    }
    public static void addDiscountCodes(CodedDiscount codedDiscount) {
        allDiscountCodes.add(codedDiscount);
    }

    public static void setAllUsers(ArrayList<Person> allUsers) {
        Database.allUsers = allUsers;
    }

    public static void setAllCategory(ArrayList<Category> allCategory) {
        Database.allCategory = allCategory;
    }

    public static void setAllRequest(ArrayList<Request> allRequest) {
        Database.allRequest = allRequest;
    }

    public static void setAllManagers(ArrayList<Manager> allManagers) {
        Database.allManagers = allManagers;
    }

    public static void setAllDiscountCodes(ArrayList<CodedDiscount> allDiscountCodes) {
        Database.allDiscountCodes = allDiscountCodes;
    }

    public static void deleteUser(String username) throws Exception {

        for (Person user : allUsers) {
            if(user.getUserName().equals(username)){
                allUsers.remove(user);
                return;
            }
        }
        throw new Exception("no user found");
    }
    public static ArrayList<Person> getAllUsers(){
        return allUsers;
    }
    public static void deleteCodedDiscount(String code){
        allDiscountCodes.removeIf(discountCode -> discountCode.getDiscountCode().equals(code));
    }
    public static ArrayList<Manager> getAllManagers() {
        return allManagers;
    }
    public static void addManager(Manager manager){
        allManagers.add(manager);
    }
    public static void addUser(Person person){
        allUsers.add(person);
    }
    public static void deleteCategory(String categoryName) throws Exception {
        allCategory.removeIf(category -> category.getName().equals(categoryName));
        throw new Exception("no category found");

    }
    public static void addCategory(Category category){
        allCategory.add(category);
    }
    public static void deleteRequest(Request request){
        allRequest.remove(request);
    }

    public static ArrayList<Category> getAllCategory() {
        return allCategory;
    }

    public static ArrayList<Request> getAllRequest() {
        return allRequest;
    }

}
