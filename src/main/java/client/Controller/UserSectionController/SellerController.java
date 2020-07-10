package Client.Controller.UserSectionController;

import Client.Controller.Connector;
import Client.Models.Category;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Off;
import Client.Models.Person.Buyer;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.Models.TradeLog;
import Server.Controller.UserSectionController.SellerServerController;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerController extends UserSectionController{
        private static SellerController single_instance = null;
        public static SellerController getInstance()
        {
                if (single_instance == null)
                        single_instance = new SellerController();

                return single_instance;
        }
        private SellerController(){

        }
        public ArrayList<String> getCategorySpecialFeatures(String categoryName) throws Exception {
                return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(new Object[]{categoryName}, MessageType.GET_CATEGORY_SPECIAL_FEATURES_SELLER_SECTION));
//                return SellerServerController.getInstance().getCategorySpecialFeatures(categoryName);
        }

        public ArrayList<String> getProductBuyers(String id) throws Exception {
                return (ArrayList<String>) Connector.getInstance().initializeMessage(new Message(new Object[]{id}, MessageType.GET_PRODUCT_BUYERS));
//                return SellerServerController.getInstance().getProductBuyers(id);
        }
        public String getFactoryName() throws Exception {
                return (String) Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson}, MessageType.GET_FACTORY_NAME));
//                return SellerServerController.getInstance().getFactoryName((Seller)loggedInPerson);
        }
        public ArrayList<ArrayList<String>> getSalesHistory() throws Exception {
                return (ArrayList<ArrayList<String>>) Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson}, MessageType.GET_SALES_HISTORY));
//                return SellerServerController.getInstance().getSalesHistory((Seller)loggedInPerson);
        }
        public ArrayList<TradeLog> getLogs() throws Exception {
                return (ArrayList<TradeLog>) Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson}, MessageType.GET_LOGS));
//                return SellerServerController.getInstance().getLogs((Seller)loggedInPerson);
        }
        public ArrayList<Product> getProducts() throws Exception {
                return (ArrayList<Product>) Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson}, MessageType.GET_PRODUCTS));
//                return SellerServerController.getInstance().getProducts((Seller)loggedInPerson);
        }
        public Product getProduct(String id) throws Exception {
                return (Product) Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,id}, MessageType.GET_PRODUCT_SELLER_SECTION));
//                return SellerServerController.getInstance().getProduct((Seller)loggedInPerson,id);
        }
        public ArrayList<Buyer> getBuyers(String id) throws Exception {
                return (ArrayList<Buyer>) Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,id}, MessageType.GET_BUYERS));
//                return SellerServerController.getInstance().getBuyers((Seller)loggedInPerson,id);
        }
        public void editProduct(String productId,HashMap<String ,String> edit) throws Exception {
                Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,productId,edit}, MessageType.EDIT_PRODUCT));
//                SellerServerController.getInstance().editProduct((Seller)loggedInPerson,productId,edit);
        }
        public void addProduct(ArrayList<String> productDetails) throws Exception {
                Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,productDetails}, MessageType.ADD_PRODUCT));
//                SellerServerController.getInstance().addProduct((Seller)loggedInPerson,productDetails);
        }
        public void removeProduct(String id) throws Exception {
                Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,id}, MessageType.REMOVE_PRODUCT_SELLER_SECTION));
//                SellerServerController.getInstance().removeProduct((Seller)loggedInPerson,id);
        }
        public ArrayList<Category> getCategories() throws Exception {
                return (ArrayList<Category>) Connector.getInstance().initializeMessage(new Message(null, MessageType.GET_CATEGORIES));
//                return SellerServerController.getInstance().getCategories();
        }
        public ArrayList<Off> getOffs() throws Exception {
                return (ArrayList<Off>) Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson}, MessageType.GET_OFFS));
//                return SellerServerController.getInstance().getOffs((Seller)loggedInPerson);
        }
        public Off getOff(String id) throws Exception {
                return (Off) Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,id}, MessageType.GET_OFF));
//                return SellerServerController.getInstance().getOff((Seller)loggedInPerson,id);
        }
        public void editOff(String offId,HashMap<String ,String> edit) throws Exception {
                Connector.getInstance().initializeMessage(new Message(new Object[]{offId,loggedInPerson,edit}, MessageType.EDIT_OFF));
//                SellerServerController.getInstance().editOff(offId,(Seller)loggedInPerson,edit);
        }
        public void addOff(ArrayList<String> offDetails) throws Exception {
                Connector.getInstance().initializeMessage(new Message(new Object[]{loggedInPerson,offDetails}, MessageType.ADD_OFF));
//                SellerServerController.getInstance().addOff((Seller)loggedInPerson,offDetails);
        }


}
