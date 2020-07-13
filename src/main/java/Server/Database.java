package Server;

import Client.Models.*;
import Client.Models.Chat.ChatBox;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Database implements Serializable {
    private static Database database;
    private ArrayList<Category> allCategory=new ArrayList<>();
    private ArrayList<Request> allRequest=new ArrayList<>();
    private ArrayList<Person> allUsers=new ArrayList<>();
    private ArrayList<CodedDiscount> allDiscountCodes=new ArrayList<>();
    private ArrayList<Off>allOffs=new ArrayList<>();
    private ArrayList<ChatBox> chatBoxes=new ArrayList<>();

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }
    public static void setInstance(Database newDatabase){
        database = newDatabase;
    }
    private Database(){

    }

    public ArrayList<ChatBox> getChatBoxes() {
        return chatBoxes;
    }
    public void addChatBox(ChatBox chatBox){
        chatBoxes.add(chatBox);
        ServerSaver.write(AllCommands.allCategory);
    }

    public ArrayList<Off> getAllOffs() {
        return allOffs;
    }

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> allProducts = new ArrayList<>();
        for (Category category : allCategory) {
            allProducts.addAll(category.getProducts());
        }
        return allProducts;
    }
    public ArrayList<Manager> getAllManagers(){
        ArrayList<Manager> allManagers=new ArrayList<>();
        for (Person user : allUsers) {
            if(user instanceof Manager)
                allManagers.add((Manager) user);
        }
        return allManagers;
    }


    public ArrayList<Seller> getAllSellers(){
         ArrayList<Seller> allSellers=new ArrayList<>();
        for (Person user : allUsers) {
            if(user instanceof Seller)
                allSellers.add((Seller) user);
        }
        return allSellers;
    }

    public ArrayList<Buyer> getAllBuyers(){
        ArrayList<Buyer> allBuyers=new ArrayList<>();
        for (Person user : allUsers) {
            if(user instanceof Buyer)
                allBuyers.add((Buyer) user);
        }
        return allBuyers;
    }


    public Category getCategoryByName(String name) throws Exception {
        for (Category category : allCategory) {
            if(category.getName().equals(name)){
                return category;
            }
        }
        throw new Exception("No category found with this name");
    }
    public Person getPersonByUsername(String username) throws Exception {
        for (Person user : allUsers) {
            if(user.getUserName().equalsIgnoreCase(username))
                return user;
        }
        throw new Exception("wrong username");
    }

    public CodedDiscount getCodedDiscountByCode(String code) throws Exception {
        for (CodedDiscount codedDiscount : allDiscountCodes) {
            if(codedDiscount.getDiscountCode().equals(code))
                return codedDiscount;
        }
        throw new Exception("wrong discount code");
    }

    public Product getProductById(String id) throws Exception {
        for (Product product : getAllProducts()) {
           if(product.getProductId().equals(id))
               return product;
        }
        throw new Exception("No product found with this id");
    }

    public ArrayList<CodedDiscount> getAllDiscountCodes() {
        return allDiscountCodes;
    }
    public void addDiscountCodes(CodedDiscount codedDiscount) {
        allDiscountCodes.add(codedDiscount);
        ServerSaver.write(AllCommands.allDiscountCodes);
    }

    public void setAllUsers(ArrayList<Person> allUsers) {
        this.allUsers = allUsers;
    }

    public void setAllCategory(ArrayList<Category> allCategory) {
        this.allCategory = allCategory;
    }

    public void setAllRequest(ArrayList<Request> allRequest) {
        this.allRequest = allRequest;
    }

    public void setAllDiscountCodes(ArrayList<CodedDiscount> allDiscountCodes) {
        this.allDiscountCodes = allDiscountCodes;
    }

    public void setAllOffs(ArrayList<Off> allOffs) {
        this.allOffs = allOffs;
    }
    public void deleteUser(String username) throws Exception {

        for (Person user : allUsers) {
            if(user.getUserName().equals(username)){
                allUsers.remove(user);
                ServerSaver.write(AllCommands.allUsers);
                return;
            }
        }
        throw new Exception("no user found");
    }
    public ArrayList<Person> getAllUsers(){
        return allUsers;
    }
    public void deleteCodedDiscount(String code) throws Exception {
        for (CodedDiscount discountCode : allDiscountCodes) {
            if(discountCode.getDiscountCode().equals(code)) {
                allDiscountCodes.remove(discountCode);
                ServerSaver.write(AllCommands.allDiscountCodes);
                return;
            }
        }
        throw new Exception("invalid discount code");
    }
    public void addUser(Person person) throws Exception {
        for (Person user : allUsers) {
            if(user.getUserName().equalsIgnoreCase(person.getUserName()))
                throw new Exception("username Exists");
        }
        allUsers.add(person);
        ServerSaver.write(AllCommands.allUsers);
    }
    public void deleteCategory(String categoryName) throws Exception {
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

    public void addCategory(Category category){

        allCategory.add(category);
        ServerSaver.write(AllCommands.allCategory);
    }

    public ArrayList<Category> getAllCategory() {
        return allCategory;
    }
    public ArrayList<Request> getAllRequest() {
        return allRequest;
    }
    public void addProduct(Product product) throws Exception {
        for (Category category : allCategory) {
            if(category.getName().equals(product.getCategory().getName())){
                category.addProduct(product);
                ServerSaver.write(AllCommands.allCategory);
                return;
            }
        }
        throw new Exception("no category found while adding product to database");
    }
    public void removeProduct(Product product) throws Exception {
        for (Category category : allCategory) {
            if(category.getName().equals(product.getCategory().getName())){
                category.removeProduct(product);
                ServerSaver.write(AllCommands.allCategory);
                return;
            }
        }
        throw new Exception("no category found while adding product to database");
    }
    public Seller getSellerByUsername(String username) throws NullPointerException{
        for (Person user : allUsers) {
            if(user instanceof Seller){
                if(user.getUserName().equals(username))
                    return (Seller)user;}
        }
        throw new NullPointerException("No seller found with this userName");
    }
    public void addOff(Off off){
        allOffs.add(off);
        ServerSaver.write(AllCommands.allOffs);
    }
    public Request getRequestByRequestId(String requestId) throws Exception {
        for (Request request : allRequest) {
            if(request.getRequestId().equals(requestId))
                return request;
        }
        throw new Exception("no request matched");
    }
    public void addRequest(Request request){
        allRequest.add(request);
        ServerSaver.write(AllCommands.allRequests);
    }
    public ArrayList<Product> getAllOffProducts(){
        ArrayList<Product> allOffProducts=new ArrayList<>();
        ArrayList<Off> offsToDelete=new ArrayList<>();
        Date date=new Date();
        for (Off off : allOffs) {
            if (date.after(off.getEndDate())) {
                offsToDelete.add(off);
            }
        }
        deleteOutOfDateOffs(offsToDelete);
        for (Off off : allOffs) {
            allOffProducts.addAll(off.getProducts());
        }
        return allOffProducts;
    }
    public Off getOffById(String Id) throws Exception {
        for (Off off : allOffs) {
            if(off.getOffId().equals(Id))
                return off;
        }
        throw new Exception("wrong off Id");
    }
    public void removeRequest(Request request) throws Exception {
        if(!allRequest.remove(request))
            throw new Exception("no request exists like this anymore");
        else
            ServerSaver.write(AllCommands.allRequests);

    }
    public void deleteOutOfDateOffs(ArrayList<Off> offsToDelete){
        allOffs.removeAll(offsToDelete);
        ServerSaver.write(AllCommands.allOffs);
    }

}
