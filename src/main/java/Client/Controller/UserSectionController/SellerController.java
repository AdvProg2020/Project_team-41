package Client.Controller.UserSectionController;

import Client.Models.Category;
import Client.Models.Off;
import Client.Models.Person.Buyer;
import Client.Models.Product;
import Client.Models.TradeLogs;
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
        public String getFactoryName(){
                System.err.println("fail");
                return new String();
        }
        public ArrayList<TradeLogs> getLogs(){
                System.err.println("fail");
                return new ArrayList<>();
        }
        public ArrayList<Product> getProducts(){
                System.err.println("fail");
                return new ArrayList<>();
        }
        public Product getProduct(int id){
                System.err.println("fail");
                return new Product();
        }
        public ArrayList<Buyer> getBuyers(int id){
                System.err.println("fail");
                return new ArrayList<>();
        }
        public void editProduct(HashMap<String ,String> edit){

        }
        public void addProduct(Product product){

        }
        public void removeProduct(int id){

        }
        public ArrayList<Category> getCategories(){
                System.err.println("fail");
                return new ArrayList<>();
        }
        public ArrayList<Off> getOffs(){
                System.err.println("fail");
                return new ArrayList<>();
        }
        public Off getOff(int id){
                System.err.println("fail");
                return new Off();
        }
        public void editOff(HashMap<String ,String> edit){

        }


}
