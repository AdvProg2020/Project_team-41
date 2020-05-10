package Client.Controller.UserSectionController;

import Client.Models.Category;
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
        public ArrayList<String> getProductBuyers(String id) throws Exception {
                return SellerServerController.getInstance().getProductBuyers(id);
        }
        public String getFactoryName(){
                return SellerServerController.getInstance().getFactoryName((Seller)loggedInPerson);
        }
        public ArrayList<String> getSalesHistory(){
                return SellerServerController.getInstance().getSalesHistory((Seller)loggedInPerson);
        }
        public ArrayList<TradeLog> getLogs(){
                return SellerServerController.getInstance().getLogs((Seller)loggedInPerson);
        }
        public ArrayList<Product> getProducts(){
                return SellerServerController.getInstance().getProducts((Seller)loggedInPerson);
        }
        public Product getProduct(String id){
                return SellerServerController.getInstance().getProduct((Seller)loggedInPerson,id);
        }
        public ArrayList<Buyer> getBuyers(String id) throws Exception {
                return SellerServerController.getInstance().getBuyers((Seller)loggedInPerson,id);
        }
        public void editProduct(String productId,HashMap<String ,String> edit) throws Exception {
                SellerServerController.getInstance().editProduct((Seller)loggedInPerson,productId,edit);
        }
        public void addProduct(ArrayList<String> productDetails) throws Exception {
                SellerServerController.getInstance().addProduct((Seller)loggedInPerson,productDetails);
        }
        public void removeProduct(String id) throws Exception {
                SellerServerController.getInstance().removeProduct((Seller)loggedInPerson,id);
        }
        public ArrayList<Category> getCategories(){
                return SellerServerController.getInstance().getCategories((Seller)loggedInPerson);
        }
        public ArrayList<Off> getOffs(){
                return SellerServerController.getInstance().getOffs((Seller)loggedInPerson);
        }
        public Off getOff(String id){
                return SellerServerController.getInstance().getOff((Seller)loggedInPerson,id);
        }
        public void editOff(String offId,HashMap<String ,String> edit){
                SellerServerController.getInstance().editOff(offId,(Seller)loggedInPerson,edit);
        }
        public void addOff(ArrayList<String> offDetails){
                SellerServerController.getInstance().addOff((Seller)loggedInPerson,offDetails);
        }


}
