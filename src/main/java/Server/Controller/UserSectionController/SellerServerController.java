package Server.Controller.UserSectionController;

import Client.Models.*;
import Client.Models.Person.Buyer;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Server.Controller.TimeControl;
import Server.Database;
import java.util.ArrayList;
import java.util.Date;
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
                Product product = Database.getProductById(productId);
                Product editedProduct = product.cloneProduct();
                for (String editRequestKey : edit.keySet()) {
                        String editRequestValue = edit.get(editRequestKey);
                        switch (editRequestKey){
                                case "seller" :{
                                        editedProduct.setSeller(Database.getSellerByUsername(editRequestValue));
                                        break;
                                }
                                case "price" :{
                                        editedProduct.setPrice(Integer.parseInt(editRequestValue));
                                        break;
                                }
                                case "companyName" :{
                                        editedProduct.setCompanyName(editRequestValue);
                                        break;
                                }
                                case "description" :{
                                        editedProduct.setDescription(editRequestValue);
                                        break;
                                }
                                case "name" :{
                                        editedProduct.setName(editRequestValue);
                                        break;
                                }
                        }
                Request request = new Request(seller,product,editedProduct);
                Database.addRequest(request);
                }
        }
        public ArrayList<String> getSalesHistory(Seller seller) throws Exception {
                ArrayList<String> salesHistory = new ArrayList<>();
                if (seller.getTradeLogs().size() == 0)
                        throw new Exception("no sale history available");
                for (TradeLog tradeLog : seller.getTradeLogs()) {
                        salesHistory.add("log id : " + tradeLog.getLogId());
                        salesHistory.add("buyer : " + tradeLog.getBuyerName());
                        salesHistory.add("date : " + TimeControl.convertGregorianToJalali(tradeLog.getDate()).toString());
                        salesHistory.add("delivery situation : " + tradeLog.getDeliverySituation());
                        salesHistory.add("off Amount : " + tradeLog.getOffAmount());
                        salesHistory.add("money : " + tradeLog.getMoney());

                }
                return  salesHistory;
        }
        public void addProduct(Seller seller,ArrayList<String> productDetails) throws Exception {
                Product product = new Product();
                String productName = productDetails.get(0);
                int productQuantity;
                int productPrice;
                try {
                        productQuantity = Integer.parseInt(productDetails.get(1));
                        productPrice = Integer.parseInt(productDetails.get(3));
                } catch (NumberFormatException e) {
                        throw new Exception("please enter a valid number");
                }
                product.setName(productName);
                for (Product otherProduct : Database.getAllProducts()) {
                        if(otherProduct.getName().equals(productName))
                                throw new Exception("name is already chosen for another product");
                }
                product.setQuantity(productQuantity);
                product.setCompanyName(productDetails.get(2));
                product.setPrice(productPrice);
                product.setCategory(Database.getCategoryByName(productDetails.get(4)));
                product.setDescription(productDetails.get(5));
                product.setSeller(seller);
                Database.addRequest(new Request(seller,product,RequestType.ADD_PRODUCT));
        }
        public void removeProduct(Seller seller,String id) throws Exception {
                Product productToBeRemoved = Database.getProductById(id);
                Request request = new Request(seller,productToBeRemoved,RequestType.REMOVE_PRODUCT);
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
        public Off getOff(Seller seller,String id) throws Exception {
                for (Off off : seller.getOffs()) {
                        if(off.getOffId().equals(id))
                                return off;
                }
                throw new Exception("wrong off Id");
        }
        public Product getProduct(Seller seller,String id) throws Exception {
                for (Product product : seller.getProducts()) {
                        if(product.getProductId().equals(id))
                                return product;
                }
                throw new Exception("wrong product id");
        }
        public ArrayList<Category> getCategories(){
                return Database.getAllCategory();
        }
        public ArrayList<Off> getOffs(Seller seller){
                return seller.getOffs();
        }
        public void editOff(String offId,Seller seller,HashMap<String ,String> edit) throws Exception {
                Off off = Database.getOffById(offId);
                Off editedOff = off.cloneOff();
                for (String editRequestKey : edit.keySet()) {
                        String editRequestValue = edit.get(editRequestKey);
                        switch (editRequestKey){
                                case "startDate" :{
                                        editedOff.setStartDate(TimeControl.getDateByDateTime(editRequestValue.split(",")));
                                        break;
                                }
                                case "endDate" :{
                                        editedOff.setEndDate(TimeControl.getDateByDateTime(editRequestValue.split(",")));
                                        break;
                                }
                                case "amountOfDiscount" :{
                                        editedOff.setAmountOfDiscount(Integer.parseInt(editRequestValue));
                                        break;
                                }
                                case "products" :{
                                        ArrayList<Product> products = new ArrayList<>();

                                        for (String productId : editRequestValue.split(",")) {
                                                Product product = Database.getProductById(productId);
                                                if(product.getIsItInOff() && !off.hasProduct(product))
                                                        throw new Exception("a product is already in an off");
                                                products.add(product);

                                        }
                                        editedOff.setProducts(products);
                                }
                        }
                }

                Request request = new Request(seller,off,editedOff);
                Database.addRequest(request);
        }
        public void addOff(Seller seller,ArrayList<String> offDetails) throws Exception {
                ArrayList<Product> allProducts = new ArrayList<>();
                for (int i = 5; i < offDetails.size(); i++) {
                        Product product = Database.getProductById(offDetails.get(i));
                        if(product.getIsItInOff())
                                throw new Exception("a product is already in an off");
                        allProducts.add(product);
                }

                int discountAmount;
                try {
                        discountAmount = Integer.parseInt(offDetails.get(4));
                } catch (NumberFormatException e) {
                        throw new Exception("please enter valid discount code!");
                }
                String[] dateTime = {offDetails.get(0),offDetails.get(1)};
                Date exactStartDate = TimeControl.getDateByDateTime(dateTime);
                dateTime = new String[]{offDetails.get(2), offDetails.get(3)};
                Date exactEndDate = TimeControl.getDateByDateTime(dateTime);

                Off off  = new Off(allProducts,Situation.CREATING,exactStartDate,exactEndDate,discountAmount,seller);

                Database.addRequest(new Request(seller,off));

        }



}
