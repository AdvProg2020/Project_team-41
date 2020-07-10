package Client.Controller.UserSectionController;

import Client.Controller.Connector;
import Client.Models.Message.Message;
import Client.Models.Person.Person;
import Client.Models.Product;
import Server.Controller.UserSectionController.ManagerServerController;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerController extends UserSectionController{
    private static ManagerController single_instance = null;
    public static ManagerController getInstance()
    {
        if (single_instance == null)
            single_instance = new ManagerController();

        return single_instance;
    }
    public void acceptAllRequests() throws Exception {
        Message message = new Message(null);
        Connector.getInstance().sendMessage(message);
        Connector.getInstance().receiveMessage();

        ManagerServerController.getInstance().acceptAllRequests();
    }
    private ManagerController(){
    }
    public void editCategorySpecialFeatures(String category,String editedField) throws Exception {
        Message message = new Message(new Object[]{category, editedField});
        Connector.getInstance().sendMessage(message);
        Connector.getInstance().receiveMessage();

        ManagerServerController.getInstance().editCategorySpecialFeatures(category,editedField);
    }
    public ArrayList<Product> getAllProducts(){
        return ManagerServerController.getInstance().getAllProducts();
    }
    public ArrayList<String> getAllUsers(){
        return ManagerServerController.getInstance().getAllUsers();
    }
    public Person getUserByUsername(String username) throws Exception {
        return ManagerServerController.getInstance().getUserByUsername(username);
    }
    public void deleteUser(String username) throws Exception {
        ManagerServerController.getInstance().deleteUser(username);
    }
    public void createManagerProfile(ArrayList<String> userInfo) throws Exception {
        ManagerServerController.getInstance().createManagerProfile(userInfo);
    }
    public void removeProduct(String productId) throws Exception {
        ManagerServerController.getInstance().removeProduct(productId);
    }
    public void createDiscountCode(ArrayList<String> codeInformation) throws Exception {
        ManagerServerController.getInstance().createDiscountCode(codeInformation);
    }
    public ArrayList<String> viewDiscountCode(String code) throws Exception {
        return ManagerServerController.getInstance().viewDiscountCode(code);
    }
    public void  editDiscountCode(String code,HashMap<String,String> edits) throws Exception {
        ManagerServerController.getInstance().editDiscountCode(code,edits);
    }
    public void removeDiscountCode(String code) throws Exception {
        ManagerServerController.getInstance().removeDiscountCode(code);
    }
    public ArrayList<String> showRequest(){
        return ManagerServerController.getInstance().showRequest();
    }
    public ArrayList<String> getRequestDetails(String request) throws Exception {
        return ManagerServerController.getInstance().getRequestDetails(request);
    }
    public void acceptRequest(String request) throws Exception {
        ManagerServerController.getInstance().acceptRequest(request);
    }
    public void declineRequest(String request) throws Exception {
        ManagerServerController.getInstance().declineRequest(request);
    }
    public ArrayList<String> showCategories(){
        return ManagerServerController.getInstance().showCategories();
    }
    public void editCategoryName(String category, String editedField) throws Exception {
        ManagerServerController.getInstance().editCategoryName(category, editedField);
    }
    public void addCategory(String categoryName,String specialFeatures) throws Exception {
        ManagerServerController.getInstance().addCategory(categoryName,specialFeatures);
    }
    public void removeCategory(String categoryName) throws Exception {
        ManagerServerController.getInstance().removeCategory(categoryName);
    }
    public ArrayList<String> getCategorySpecialFeatures(String categoryName) throws Exception {
        return ManagerServerController.getInstance().getCategorySpecialFeatures(categoryName);
    }
    public ArrayList<String> viewAllDiscountCodes(){
        return ManagerServerController.getInstance().viewAllDiscountCodes();
    }
    public Product getProductById(String productId) throws Exception {
        return ManagerServerController.getInstance().getProductById(productId);
    }
}