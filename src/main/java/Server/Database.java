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
    private static ArrayList<Off>allOffs=new ArrayList<>();

    public static ArrayList<Off> getAllOffs() {
        return allOffs;
    }
//todo move all here

    public static ArrayList<Product> getAllProducts(){
        ArrayList<Product> allProducts = new ArrayList<>();
        for (Category category : allCategory) {
            allProducts.addAll(category.getProducts());
        }
        return allProducts;
    }

    public static ArrayList<Seller> getAllSellers(){
         ArrayList<Seller> allSellers=new ArrayList<>();
        for (Person user : allUsers) {
            if(user instanceof Seller)
                allSellers.add((Seller) user);
        }
        return allSellers;
    }
    public static Category getCategoryByName(String name) throws Exception {
        for (Category category : allCategory) {
            if(category.getName().equals(name)){
                return category;
            }
        }
        throw new Exception("No category found with this name");
    }
    public static Person getPersonByUsername(String username) throws Exception {
        for (Person user : allUsers) {
            if(user.getUserName().equalsIgnoreCase(username))
                return user;
        }
        throw new Exception("wrong username");
    }

    public static CodedDiscount getCodedDiscountByCode(String code) throws Exception {
        for (CodedDiscount codedDiscount : allDiscountCodes) {
            if(codedDiscount.equals(code))
                return codedDiscount;
        }
        throw new Exception("wrong discount code");
    }

    public static Product getProductById(String id) throws Exception {
        for (Product product : getAllProducts()) {
           if(product.getProductId().equals(id))
               return product;
        }
        throw new Exception("No product found with this id");
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
    public static void addProduct(Product product){
        for (Category category : allCategory) {
            if(category.getName().equals(product.getCategory().getName())){
                category.addProduct(product);
                return;
            }
        }
        throw new NullPointerException("no category found while adding product to database");
    }
    public static void removeProduct(Product product){
        for (Category category : allCategory) {
            if(category.equals(product.getCategory())){
                category.removeProduct(product);
                return;
            }
        }
        throw new NullPointerException("no category found while adding product to database");
    }
    public static Seller getSellerByUsername(String username) throws NullPointerException{
        for (Person user : allUsers) {
            if(user instanceof Seller){
                if(user.getUserName().equals(username))
                    return (Seller)user;}
        }
        throw new NullPointerException("No seller found with this userName");
    }
    public static void addOff(Off off){
        allOffs.add(off);
    }
    public static Request getRequestByRequestId(String requestId) throws Exception {
        for (Request request : allRequest) {
            if(request.getRequestId().equals(requestId))
                return request;
        }
        throw new Exception("no request matched");
    }
    public static void addRequest(Request request){
        allRequest.add(request);
    }
    public static ArrayList<Product> getAllOffProducts(){
        ArrayList<Product> allOffProducts=new ArrayList();
        for (Off off : allOffs) {
            for (Product product : off.getProducts()) {
                allOffProducts.add(product);
            }
        }
        return allOffProducts;
    }
    public static Off getOffById(String Id) throws Exception {
        for (Off off : allOffs) {
            if(off.getOffId().equals(Id))
                return off;
        }
        throw new Exception("wrong off Id");
    }

}
