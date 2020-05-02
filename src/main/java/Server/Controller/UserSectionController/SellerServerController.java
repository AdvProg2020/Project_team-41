package Server.Controller.UserSectionController;

import Client.Models.*;
import Client.Models.Person.Buyer;
import Client.Models.Person.Seller;
import Server.Controller.TimeControl;
import Server.Database;

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
        public ArrayList<String> getProductBuyers(int id){
                System.err.println("failed");
                return null;
        }
        public void editProduct(Seller seller,int productId,HashMap<String ,String> edit){

        }
        public ArrayList<String> getSalesHistory(Seller seller){
                ArrayList<String> salesHistory = new ArrayList<>();
                for (TradeLog tradeLog : seller.getTradeLogs()) {
                        salesHistory.add("log id : " + tradeLog.getLogId());
                        salesHistory.add("buyer : " + tradeLog.getBuyerName());
                        salesHistory.add("date : " + TimeControl.convertGregorianToJalali(tradeLog.getDate()).toString());
                        salesHistory.add("delivery situation : " + tradeLog.getDeliverySituation());
                        salesHistory.add("off Amount : " + tradeLog.getOffAmount());
                        salesHistory.add("money : " + Integer.toString(tradeLog.getMoney()));

                }
                return  salesHistory;
        }
        public void addProduct(Seller seller,Product product){
                Database.addRequest(new Request(null,RequestType.ADD_PRODUCT,product,seller,null));

        }
        public void removeProduct(Seller seller,int id){

        }
        public ArrayList<Product> getProducts(Seller seller){
                System.err.println("failed");
                return null;
        }
        public ArrayList<TradeLog> getLogs(Seller seller){
                System.err.println("failed");
                return null;
        }
        public String getFactoryName(Seller seller){
                System.err.println("failed");
                return null;
        }
        public ArrayList<Buyer> getBuyers(Seller seller,int id){
                System.err.println("fail");
                return new ArrayList<>();
        }
        public Off getOff(Seller seller,int id) {
                System.err.println("failed");
                return null;
        }
        public Product getProduct(Seller seller,int id){
                System.err.println("failed");
                return null;
        }
        public ArrayList<Category> getCategories(Seller seller){
                System.err.println("fail");
                return new ArrayList<>();
        }
        public ArrayList<Off> getOffs(Seller seller){
                System.err.println("fail");
                return new ArrayList<>();
        }
        public void editOff(Seller seller,HashMap<String ,String> edit){

        }


}
