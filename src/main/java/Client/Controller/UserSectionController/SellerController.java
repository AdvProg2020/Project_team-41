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
        public ArrayList<String> getProductBuyers(int id){
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
        public Product getProduct(int id){
                return SellerServerController.getInstance().getProduct((Seller)loggedInPerson,id);
        }
        public ArrayList<Buyer> getBuyers(int id){
                return SellerServerController.getInstance().getBuyers((Seller)loggedInPerson,id);
        }
        public void editProduct(int productId,HashMap<String ,String> edit){
                SellerServerController.getInstance().editProduct((Seller)loggedInPerson,productId,edit);
        }
        public void addProduct(ArrayList<String> productDetails){
                SellerServerController.getInstance().addProduct((Seller)loggedInPerson,productDetails);
        }
        public void removeProduct(int id){
                SellerServerController.getInstance().removeProduct((Seller)loggedInPerson,id);
        }
        public ArrayList<Category> getCategories(){
                return SellerServerController.getInstance().getCategories((Seller)loggedInPerson);
        }
        public ArrayList<Off> getOffs(){
                return SellerServerController.getInstance().getOffs((Seller)loggedInPerson);
        }
        public Off getOff(int id){
                return SellerServerController.getInstance().getOff((Seller)loggedInPerson,id);
        }
        public void editOff(HashMap<String ,String> edit){
                SellerServerController.getInstance().editOff((Seller)loggedInPerson,edit);
        }


}
