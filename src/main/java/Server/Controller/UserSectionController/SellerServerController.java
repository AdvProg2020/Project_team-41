package Server.Controller.UserSectionController;

import Client.Controller.UserSectionController.SellerController;
import Client.Models.*;
import Client.Models.Person.Buyer;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Server.Controller.TimeControl;
import Server.Database;

import javax.xml.crypto.Data;
import java.awt.image.DataBuffer;
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
        public ArrayList<String> getProductBuyers(String id) throws Exception {
                Product product = Database.getProductById(id);
                ArrayList<String> productBuyers = new ArrayList<>();
                for (Person buyer : product.getBuyers()) {
                    productBuyers.add(buyer.getUserName());
                }
                return productBuyers;
        }
        public void editProduct(Seller seller,String productId,HashMap<String ,String> edit) throws Exception {
                Request request = new Request(edit,RequestType.EDIT_PRODUCT,Database.getProductById(productId),seller,null);
                Database.addRequest(request);
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
        public void addProduct(Seller seller,ArrayList<String> productDetails) throws Exception {
                Product product = new Product();
                product.setName(productDetails.get(0));
                product.setQuantity(Integer.parseInt(productDetails.get(1)));
                product.setCompanyName(productDetails.get(2));
                product.setPrice(Integer.parseInt(productDetails.get(3)));
                product.setCategory(Database.getCategoryByName(productDetails.get(4)));
                product.setDescription(productDetails.get(5));
                product.setSeller(seller);
                Database.addRequest(new Request(null,RequestType.ADD_PRODUCT,product,seller,null));
        }
        public void removeProduct(Seller seller,String id) throws Exception {
                Product productToBeRemoved = Database.getProductById(id);
                Request request = new Request(null,RequestType.REMOVE_PRODUCT,productToBeRemoved,seller,null);
                Database.addRequest(request);
        }
        public ArrayList<Product> getProducts(Seller seller){
                return seller.getProducts();
        }
        public ArrayList<TradeLog> getLogs(Seller seller){
                return seller.getTradeLogs();
        }
        public String getFactoryName(Seller seller){
                return seller.getFactoryName();
        }
        public ArrayList<Buyer> getBuyers(Seller seller,String id) throws Exception {
                return Database.getProductById(id).getBuyers();
        }
        public Off getOff(Seller seller,String id) {
                for (Off off : seller.getOffs()) {
                        if(off.getOffId().equals(id))
                                return off;
                }
                return null;
        }
        public Product getProduct(Seller seller,String id){
                for (Product product : seller.getProducts()) {
                        if(product.getProductId().equals(id))
                                return product;
                }
                return null;
        }
        public ArrayList<Category> getCategories(Seller seller){
                return Database.getAllCategory();
        }
        public ArrayList<Off> getOffs(Seller seller){
                return seller.getOffs();
        }
        public void editOff(String offId,Seller seller,HashMap<String ,String> edit){
                Request request = new Request(edit,RequestType.EDIT_OFF,null,seller,Database.getOffById(offId));
                Database.addRequest(request);
        }
        public void addOff(Seller seller,ArrayList<String> offDetails){
                Off off ;
                        //= new Off(,,TimeControl.);

                //Database.addRequest(new Request(null,RequestType.ADD_OFF,null,seller,off));

        }



}
