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
                        switch (editRequestKey.toLowerCase()){
                                case "price" :{
                                        try {
                                                editedProduct.setPrice(Integer.parseInt(editRequestValue));
                                        } catch (NumberFormatException e) {
                                                throw new Exception("wrong price!");
                                        }
                                        break;
                                }
                                case "companyname" :{
                                        editedProduct.setCompanyName(editRequestValue);
                                        break;
                                }
                                case "description" :{
                                        editedProduct.setDescription(editRequestValue);
                                        break;
                                }
                                case "name" :{
                                        for (Product otherProduct : Database.getAllProducts()) {
                                                if(otherProduct.getName().equals(editRequestValue))
                                                        throw new Exception("product name is used");
                                        }
                                        editedProduct.setName(editRequestValue);
                                        break;
                                }
                                case "specialfeature" : {
                                        boolean flagForCategoryName = false;
                                        HashMap<String, SpecialFeature> specialFeatures = new HashMap<>();
                                        for (String rawSpecialFeature : editRequestValue.split(",")) {
                                                String[] specialFeature = rawSpecialFeature.split("-");
                                                for (String feature : product.getCategory().getSpecialFeatures()) {
                                                        if(feature.equalsIgnoreCase(specialFeature[0])){
                                                                try {
                                                                        String productSpecialFeature = specialFeature[1];
                                                                        if(specialFeatures.put(feature,new SpecialFeature(specialFeature[1])) != null)
                                                                                throw new Exception("you entered more than one specialFeature for one category specialFeature");
                                                                        flagForCategoryName = true;
                                                                        break;

                                                                } catch (Exception e) {
                                                                        if(e.getMessage().equals("you entered more than one specialFeature for one category specialFeature"))
                                                                                throw e;
                                                                        throw new Exception("please enter special features as i said");
                                                                }
                                                        }

                                                }
                                                if(!flagForCategoryName)
                                                        throw new Exception("no category specialFeature matched what you gave us");
                                                flagForCategoryName = false;
                                        }
                                }
                                default:{
                                        throw new Exception("wrong field");
                                }
                        }
                        product.setSituation(Situation.EDITING);
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
                boolean flagForCategoryName = false;
                Category category = Database.getCategoryByName(productDetails.get(4));
                HashMap<String, SpecialFeature> specialFeatures = new HashMap<>();
                for (String rawSpecialFeature : productDetails.get(6).split(",")) {
                        String[] specialFeature = rawSpecialFeature.split("-");
                        for (String feature : category.getSpecialFeatures()) {
                                if(feature.equalsIgnoreCase(specialFeature[0])){
                                        try {
                                                String productSpecialFeature = specialFeature[1];
                                                if(specialFeatures.put(feature,new SpecialFeature(productSpecialFeature)) != null)
                                                        throw new Exception("you entered more than one specialFeature for one category specialFeature");
                                                flagForCategoryName = true;
                                                break;

                                        } catch (Exception e) {
                                                throw new Exception("please enter special features as i said");
                                        }
                                }

                        }
                        if(!flagForCategoryName)
                                throw new Exception("no category specialFeature matched what you gave us");
                        flagForCategoryName = false;
                }

                int productQuantity;
                int productPrice;
                try {
                        productQuantity = Integer.parseInt(productDetails.get(1));
                        productPrice = Integer.parseInt(productDetails.get(3));
                } catch (NumberFormatException e) {
                        throw new Exception("please enter a valid number");
                }
                product.setQuantity(productQuantity);
                product.setPrice(productPrice);
                product.setName(productName);

                for (Product otherProduct : Database.getAllProducts()) {
                        if(otherProduct.getName().equals(productName))
                                throw new Exception("name is already chosen for another product");
                }
                product.setCategory(category);
                product.setCompanyName(productDetails.get(2));
                product.setDescription(productDetails.get(5));
                product.setSeller(seller);
                product.setSpecialFeatures(specialFeatures);
                product.setSituation(Situation.CREATING);
                Database.addRequest(new Request(seller,product,RequestType.ADD_PRODUCT));
        }
        public ArrayList<String> getCategorySpecialFeatures(String categoryName) throws Exception {
                return Database.getCategoryByName(categoryName).getSpecialFeatures();
        }
        public void removeProduct(Seller seller,String id) throws Exception {
                Product productToBeRemoved = Database.getProductById(id);
                Database.removeProduct(productToBeRemoved);
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
                        switch (editRequestKey.toLowerCase()){
                                case "startdate" :{
                                        editedOff.setStartDate(TimeControl.getDateByDateTime(editRequestValue.split(",")));
                                        break;
                                }
                                case "enddate" :{
                                        editedOff.setEndDate(TimeControl.getDateByDateTime(editRequestValue.split(",")));
                                        break;
                                }
                                case "amountofdiscount" :{
                                        editedOff.setAmountOfDiscount(Integer.parseInt(editRequestValue));
                                        break;
                                }
                                case "products" :{
                                        ArrayList<Product> products = new ArrayList<>();

                                        for (String productId : editRequestValue.split(",")) {
                                                Product product = Database.getProductById(productId);
                                                if(product.isItInOff() && !off.hasProduct(product))
                                                        throw new Exception("a product is already in an off");
                                                products.add(product);

                                        }
                                        editedOff.setProducts(products);
                                }
                                default:{
                                        throw new Exception("invalid field");
                                }
                        }
                }
                off.setSituation(Situation.EDITING);
                Request request = new Request(seller,off,editedOff);
                Database.addRequest(request);
        }
        public void addOff(Seller seller,ArrayList<String> offDetails) throws Exception {
                ArrayList<Product> allProducts = new ArrayList<>();
                for (int i = 5; i < offDetails.size(); i++) {
                        Product product = Database.getProductById(offDetails.get(i));
                        if(product.isItInOff())
                                throw new Exception("a product is already in an off");
                        allProducts.add(product);
                }
                if(allProducts.isEmpty())
                        throw new Exception("off should have at least one product");

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
                if(!exactEndDate.after(exactStartDate)){
                        throw new Exception("end date should be after start date");
                }


                Off off  = new Off(allProducts,Situation.CREATING,exactStartDate,exactEndDate,discountAmount,seller);

                Database.addRequest(new Request(seller,off));

        }



}
