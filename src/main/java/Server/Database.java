package Server;

import Client.Models.Category;
import Client.Models.CodedDiscount;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Request;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Category> allCategory=new ArrayList<>();
    private static ArrayList<Request> allRequest=new ArrayList<>();
    private static ArrayList<Person> allUsers=new ArrayList<>();
    private static ArrayList<Manager>allManagers=new ArrayList<>();
    private static ArrayList<CodedDiscount> allDiscountCodes=new ArrayList<>();
    //todo move all here

    public static Category getCategoryByName(String name) throws Exception {
        for (Category category : allCategory) {
            if(category.getName().equals(name)){
                return category;
            }
        }
        throw new Exception("no category found");
    }
    public static ArrayList<CodedDiscount> getAllDiscountCodes() {
        return allDiscountCodes;
    }
    public static void addDiscountCodes(CodedDiscount codedDiscount) {
        allDiscountCodes.add(codedDiscount);
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
