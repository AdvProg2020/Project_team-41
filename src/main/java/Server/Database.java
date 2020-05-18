package Server;

import Client.Models.*;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.util.ArrayList;


public class Database implements Serializable {
    private static ArrayList<Category> allCategory=new ArrayList<>();
    private static ArrayList<Request> allRequest=new ArrayList<>();
    private static ArrayList<Person> allUsers=new ArrayList<>();
    private static ArrayList<CodedDiscount> allDiscountCodes=new ArrayList<>();
    private static ArrayList<Off>allOffs=new ArrayList<>();

    public static ArrayList<Off> getAllOffs() {
        return allOffs;
    }

    public static ArrayList<Product> getAllProducts(){
        ArrayList<Product> allProducts = new ArrayList<>();
        for (Category category : allCategory) {
            allProducts.addAll(category.getProducts());
        }
        return allProducts;
    }
    public static ArrayList<Manager> getAllManagers(){
        ArrayList<Manager> allManagers=new ArrayList<>();
        for (Person user : allUsers) {
            if(user instanceof Manager)
                allManagers.add((Manager) user);
        }
        return allManagers;
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
            if(codedDiscount.getDiscountCode().equals(code))
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
        ServerSaver.write(AllCommands.allDiscountCodes);
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

    public static void setAllDiscountCodes(ArrayList<CodedDiscount> allDiscountCodes) {
        Database.allDiscountCodes = allDiscountCodes;
    }

    public static void setAllOffs(ArrayList<Off> allOffs) {
        Database.allOffs = allOffs;
    }
    public static void deleteUser(String username) throws Exception {

        for (Person user : allUsers) {
            if(user.getUserName().equals(username)){
                allUsers.remove(user);
                ServerSaver.write(AllCommands.allUsers);
                return;
            }
        }
        throw new Exception("no user found");
    }
    public static ArrayList<Person> getAllUsers(){
        return allUsers;
    }
    public static void deleteCodedDiscount(String code) throws Exception {
        for (CodedDiscount discountCode : allDiscountCodes) {
            if(discountCode.getDiscountCode().equals(code)) {
                allDiscountCodes.remove(discountCode);
                ServerSaver.write(AllCommands.allDiscountCodes);
                return;
            }
        }
        throw new Exception("invalid discount code");
    }
    public static void addUser(Person person) throws Exception {
        for (Person user : allUsers) {
            if(user.getUserName().equalsIgnoreCase(person.getUserName()))
                throw new Exception("username Exists");
        }
        allUsers.add(person);
        ServerSaver.write(AllCommands.allUsers);
    }
    public static void deleteCategory(String categoryName) throws Exception {
        for (Category category : allCategory) {
            if(category.getName().equalsIgnoreCase(categoryName)) {
                for (Product product : category.getProducts()) {
                    product.removeProduct();
                }

                allCategory.remove(category);
                ServerSaver.write(AllCommands.allCategory);
                return;
            }
        }
        throw new Exception("no category found");

    }

    public static void addCategory(Category category){

        allCategory.add(category);
        ServerSaver.write(AllCommands.allCategory);
    }

    public static ArrayList<Category> getAllCategory() {
        return allCategory;
    }
    public static ArrayList<Request> getAllRequest() {
        return allRequest;
    }
    public static void addProduct(Product product) throws Exception {
        for (Category category : allCategory) {
            if(category.getName().equals(product.getCategory().getName())){
                category.addProduct(product);
                ServerSaver.write(AllCommands.allCategory);
                return;
            }
        }
        throw new Exception("no category found while adding product to database");
    }
    public static void removeProduct(Product product) throws Exception {
        for (Category category : allCategory) {
            if(category.getName().equals(product.getCategory().getName())){
                category.removeProduct(product);
                ServerSaver.write(AllCommands.allCategory);
                return;
            }
        }
        throw new Exception("no category found while adding product to database");
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
        ServerSaver.write(AllCommands.allOffs);
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
        ServerSaver.write(AllCommands.allRequests);
    }
    public static ArrayList<Product> getAllOffProducts(){
        ArrayList<Product> allOffProducts=new ArrayList<>();
        for (Off off : allOffs) {
            allOffProducts.addAll(off.getProducts());
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

    public static void removeRequest(Request request) throws Exception {
        if(!allRequest.remove(request))
            throw new Exception("no request exists like this anymore");
        else
            ServerSaver.write(AllCommands.allRequests);

    }
    public static void deleteOutOfDateOffs(ArrayList<Off> offsToDelete){
        allOffs.removeAll(offsToDelete);
        ServerSaver.write(AllCommands.allOffs);
    }

}
