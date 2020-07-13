package Client.Controller.UserSectionController;

import Client.Controller.Connector;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.Person;
import Client.Models.Product;
import Client.Models.TradeLog;
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

    private ManagerController(){
    }

    public void changeTradeLogToSent(String tradeLogId) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{tradeLogId}, MessageType.CHANGE_TRADE_LOG_TO_SENT));
    }

    public ArrayList<TradeLog> getTradeLogs() throws Exception {
        return (ArrayList<TradeLog>) Connector.getInstance().initializeMessage(new Message(null, MessageType.VIEW_TRADE_LOGS));
    }

    public void acceptAllRequests() throws Exception {
        Connector.getInstance().initializeMessage(new Message(null, MessageType.ACCEPT_ALL_REQUESTS));

        // ManagerServerController.getInstance().acceptAllRequests();
    }
    public void editCategorySpecialFeatures(String category,String editedField) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{category, editedField}, MessageType.EDIT_CATEGORY_SPECIAL_FEATURES));

        // ManagerServerController.getInstance().editCategorySpecialFeatures(category,editedField);
    }
    public ArrayList<Product> getAllProducts() throws Exception {
        return (ArrayList<Product>) Connector.getInstance().initializeMessage(new Message(null, MessageType.GET_ALL_PRODUCTS));

        //return ManagerServerController.getInstance().getAllProducts();
    }
    public ArrayList<String> getAllUsers() throws Exception {
        return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(null, MessageType.GET_ALL_USERS));
//        return ManagerServerController.getInstance().getAllUsers();
    }
    public Person getUserByUsername(String username) throws Exception {
        return (Person) Connector.getInstance().initializeMessage(new Message(new Object[]{username}, MessageType.GET_USER_BY_USERNAME));
        //return ManagerServerController.getInstance().getUserByUsername(username);
    }
    public void deleteUser(String username) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{username}, MessageType.DELETE_USER));
        //ManagerServerController.getInstance().deleteUser(username);
    }
    public void createManagerProfile(ArrayList<String> userInfo) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{userInfo}, MessageType.CREATE_MANAGER_PROFILE));
        //ManagerServerController.getInstance().createManagerProfile(userInfo);
    }
    public void removeProduct(String productId) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{productId}, MessageType.REMOVE_PRODUCT_MANAGER_SECTION));
//        ManagerServerController.getInstance().removeProduct(productId);
    }
    public void createDiscountCode(ArrayList<String> codeInformation) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{codeInformation}, MessageType.CREATE_DISCOUNT_CODE));
//        ManagerServerController.getInstance().createDiscountCode(codeInformation);
    }
    public ArrayList<String> viewDiscountCode(String code) throws Exception {
        return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(new Object[]{code}, MessageType.VIEW_DISCOUNT_CODE));
//        return ManagerServerController.getInstance().viewDiscountCode(code);
    }
    public void  editDiscountCode(String code,HashMap<String,String> edits) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{code,edits}, MessageType.EDIT_DISCOUNT_CODE));
//        ManagerServerController.getInstance().editDiscountCode(code,edits);
    }
    public void removeDiscountCode(String code) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{code}, MessageType.REMOVE_DISCOUNT_CODE));
//        ManagerServerController.getInstance().removeDiscountCode(code);
    }
    public ArrayList<String> showRequest() throws Exception{
        return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(null, MessageType.SHOW_REQUEST));
//        return ManagerServerController.getInstance().showRequest();
    }
    public ArrayList<String> getRequestDetails(String request) throws Exception {
        return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(new Object[]{request}, MessageType.GET_REQUEST_DETAILS));
//        return ManagerServerController.getInstance().getRequestDetails(request);
    }
    public void acceptRequest(String request) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{request}, MessageType.ACCEPT_REQUEST));
//        ManagerServerController.getInstance().acceptRequest(request);
    }
    public void declineRequest(String request) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{request}, MessageType.DECLINE_REQUEST));
//        ManagerServerController.getInstance().declineRequest(request);
    }
    public ArrayList<String> showCategories() throws Exception{
        return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(null, MessageType.SHOW_CATEGORIES));
//        return ManagerServerController.getInstance().showCategories();
    }
    public void editCategoryName(String category, String editedField) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{category,editedField}, MessageType.EDIT_CATEGORY_NAME));
//        ManagerServerController.getInstance().editCategoryName(category, editedField);
    }
    public void addCategory(String categoryName,String specialFeatures) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{categoryName,specialFeatures}, MessageType.ADD_CATEGORY));
//        ManagerServerController.getInstance().addCategory(categoryName,specialFeatures);
    }
    public void removeCategory(String categoryName) throws Exception {
        Connector.getInstance().initializeMessage(new Message(new Object[]{categoryName}, MessageType.REMOVE_CATEGORY));
//        ManagerServerController.getInstance().removeCategory(categoryName);
    }
    public ArrayList<String> getCategorySpecialFeatures(String categoryName) throws Exception {
        return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(new Object[]{categoryName}, MessageType.GET_CATEGORY_SPECIAL_FEATURES_MANAGER_SECTION));
//        return ManagerServerController.getInstance().getCategorySpecialFeatures(categoryName);
    }
    public ArrayList<String> viewAllDiscountCodes() throws Exception{
        return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(null, MessageType.VIEW_ALL_DISCOUNT_CODES));
//        return ManagerServerController.getInstance().viewAllDiscountCodes();
    }
    public Product getProductById(String productId) throws Exception {
        return (Product) Connector.getInstance().initializeMessage(new Message(new Object[]{productId}, MessageType.GET_PRODUCT_BY_ID));
//        return ManagerServerController.getInstance().getProductById(productId);
    }
}