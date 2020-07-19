package Server.Controller.UserSectionController;

import Client.Models.*;
import Client.Models.Person.Buyer;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Server.Controller.TimeControl;
import Server.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BuyerServerController {

    private static BuyerServerController single_instance = null;
    public static BuyerServerController getInstance(){
        if (single_instance == null)
            single_instance = new BuyerServerController();

        return single_instance;
    }
    private BuyerServerController(){
    }

    public static void payForTheShop(Buyer buyer) throws Exception {
        buyer = (Buyer) Database.getInstance().getPersonByUsername(buyer.getUserName());
        Cart cart = buyer.getCart();
        int cashToPay = cart.getCashToPay();
        HashMap<Seller,HashMap<Product,Integer>> sellerProducts = new HashMap<>();
        if(buyer.getCredit()<cashToPay)
            throw new Exception("you don't have enough credit");
        for (Product product : cart.getProducts().keySet()) {
            int productQuantity = cart.getProducts().get(product);
            if(product.getQuantity()<productQuantity)
                throw new Exception("oops, a product just went out of stock");
        }
        for (Product product : cart.getProducts().keySet()) {
            Seller seller = product.getSeller();
            int productQuantity = cart.getProducts().get(product);
            sellerProducts.computeIfAbsent(seller, k -> new HashMap<>());
            sellerProducts.get(seller).put(product,productQuantity);
            product.addBuyer(buyer);
        }
        buyer.decreaseCredit(cashToPay);
        for (Seller seller : sellerProducts.keySet()) {
            int money = 0;
            int moneyWithoutOff = 0;
            for (Product product : sellerProducts.get(seller).keySet()) {
                int productQuantity = sellerProducts.get(seller).get(product);
                moneyWithoutOff += product.getPrice() * productQuantity;
                money += product.getPriceWithOff() * productQuantity;
                product.decreaseQuantity(productQuantity);
                seller.increaseCreditWithWage(product.getPriceWithOff() * productQuantity);

            }
                seller.addTradeLog(new TradeLog(new Date(),money,moneyWithoutOff - money,sellerProducts.get(seller),buyer.getUserName(), TradeLog.DeliverySituation.WAITING,null));
        }
        if(cart.getCodedDiscount() != null)
            cart.getCodedDiscount().reduceDiscountCodeForUser(buyer);
        buyer.addTradeLog(new TradeLog(new Date(),cashToPay,cart.totalPrice()-cashToPay,cart.getProducts(),buyer.getUserName(), TradeLog.DeliverySituation.WAITING,cart.getReceiverInformation()));
        buyer.renewCart();
    }
    public void setReceiverInformation(Buyer buyer,ArrayList<String> receiverInformation) throws Exception {
        Buyer foundBuyer = (Buyer) Database.getInstance().getPersonByUsername(buyer.getUserName());
        foundBuyer.getCart().setReceiverInformation(receiverInformation);
    }
    public ArrayList<String> getCodedDiscounts(Person person) throws Exception {
        person = Database.getInstance().getPersonByUsername(person.getUserName());
        ArrayList<String> codedDiscounts = new ArrayList<>();
        for (CodedDiscount discountCode : Database.getInstance().getAllDiscountCodes()) {
            if(discountCode.hasPerson(person))
                codedDiscounts.add(discountCode.getDiscountCode());
        }
        return codedDiscounts;
    }
    public ArrayList<Product> getAllBoughtFiles(Buyer buyer) throws IOException {
        ArrayList<Product> boughtFiles = new ArrayList<>();
        for (TradeLog tradeLog : buyer.getTradeLogs()) {
            for (Product product : tradeLog.getItems().keySet()) {
                if (Database.getInstance().getFile(product) != null) {
                    boughtFiles.add(product);
                }
            }
        }
        return boughtFiles;
    }
    public List<Byte> downloadFile(Product product) throws Exception {
        List<Byte> file = Database.getInstance().getFile(product);
        if (file == null) {
            throw new Exception("file doesn't exist in database");
        }
        else
            return file;
    }
    public ArrayList<String> getCodedDiscount(String discountCode, Person person) throws Exception {
        person = Database.getInstance().getPersonByUsername(person.getUserName());
        CodedDiscount foundCodedDiscount = null;
        for (CodedDiscount codedDiscount : Database.getInstance().getAllDiscountCodes()) {
            if(codedDiscount.hasPerson(person))
                if (codedDiscount.getDiscountCode().equals(discountCode)) {
                    foundCodedDiscount = codedDiscount;
                    break;
                }
        }
        if (foundCodedDiscount == null) {
            return null;
        }
        ArrayList<String> discountCodeInformation = new ArrayList<>();


        discountCodeInformation.add("discount code: "+foundCodedDiscount.getDiscountCode());
        discountCodeInformation.add("start date: "+ TimeControl.getJalaliDateAndTimeForPrint(foundCodedDiscount.getStartDate()));
        discountCodeInformation.add("end date: "+TimeControl.getJalaliDateAndTimeForPrint(foundCodedDiscount.getEndDate()));
        discountCodeInformation.add("discount percentage: "+ foundCodedDiscount.getDiscountPercentage());
        discountCodeInformation.add("maximum discount: "+ foundCodedDiscount.getMaximumDiscount());
        discountCodeInformation.add("discount repeats remaining: "+ foundCodedDiscount.getDiscountCodeRemainingForUser(person));
        return discountCodeInformation;
    }
    public static void rateTheProduct(Buyer buyer,String productId , Score score) throws Exception {
        buyer = (Buyer) Database.getInstance().getPersonByUsername(buyer.getUserName());
        boolean flag = false;
        Product product = Database.getInstance().getProductById(productId);
        for (TradeLog tradeLog : buyer.getTradeLogs()) {
            if (tradeLog.getItems().containsKey(product)) {
                flag = true;
                break;
            }
        }
        if(flag)
            product.addScore(score);
        else
            throw new Exception("sorry,only people who bought the product can rate it");
    }
    public Product getProduct(String productId) throws Exception {
        return Database.getInstance().getProductById(productId);
    }
    public void addCodedDiscountToCart(Buyer buyer,String discountCode) throws Exception {
        buyer = (Buyer) Database.getInstance().getPersonByUsername(buyer.getUserName());
        CodedDiscount codedDiscount = Database.getInstance().getCodedDiscountByCode(discountCode);
        if(codedDiscount.hasPerson(buyer)) {
            if(new Date().before(codedDiscount.getStartDate()))
                throw new Exception("this discount code is not started yet");
            if(new Date().after(codedDiscount.getEndDate()))
                throw new Exception("sorry, this discount code is expired");
            buyer.getCart().setCodedDiscount(codedDiscount);
        }
        else {
            throw new Exception("you don't have this discount code");
        }
    }
    public void removeCodedDiscountFromCart(Buyer buyer) throws Exception {
        buyer = (Buyer) Database.getInstance().getPersonByUsername(buyer.getUserName());
        buyer.getCart().setCodedDiscount(null);
    }
    public void increaseProduct(Buyer buyer,int num , String productId) throws Exception {
        buyer = (Buyer) Database.getInstance().getPersonByUsername(buyer.getUserName());
        buyer.getCart().increaseProductQuantity(Database.getInstance().getProductById(productId));
    }
    public void decreaseProduct(Buyer buyer,int num , String productId) throws Exception {
        buyer = (Buyer) Database.getInstance().getPersonByUsername(buyer.getUserName());
        buyer.getCart().decreaseProductQuantity(Database.getInstance().getProductById(productId));
    }
}
