package Server.Controller.UserSectionController;

import Client.Models.Category;
import Client.Models.Off;
import Client.Models.Person.Buyer;
import Client.Models.Product;
import Client.Models.TradeLogs;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerServerController extends UserSectionServerController {
        private static SellerServerController single_instance = null;
        public static SellerServerController getInstance()
        {
                if (single_instance == null)
                        single_instance = new SellerServerController();

                return single_instance;
        }
        private SellerServerController(){

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