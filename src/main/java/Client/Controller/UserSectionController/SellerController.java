package Client.Controller.UserSectionController;

import Client.Models.Category;
import Client.Models.Off;
import Client.Models.Person.Buyer;
import Client.Models.Product;
import Client.Models.TradeLogs;

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

        }
        public ArrayList<TradeLogs> getLogs(){

        }
        public ArrayList<Product> getProducts(){

        }
        public Product getProduct(int id){

        }
        public ArrayList<Buyer> getBuyers(int id){

        }
        public void editProduct(HashMap<String ,String> edit){

        }
        public void addProduct(Product product){

        }
        public void removeProduct(int id){

        }
        public ArrayList<Category> getCategories(){

        }
        public ArrayList<Off> getOffs(){

        }
        public Off getOff(int id){

        }
        public void editOff(HashMap<String ,String> edit){

        }


}
