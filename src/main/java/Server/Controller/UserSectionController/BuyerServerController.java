package Server.Controller.UserSectionController;

import Client.Models.*;
import Client.Models.Person.Buyer;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Server.Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
        Cart cart = buyer.getCart();
        int cashToPay;
        if(cart.getCodedDiscount() == null)
            cashToPay = cart.totalPrice();
        else {
            cashToPay = cart.getCodedDiscount().howMuchWillItCost(cart.totalPrice());
        }
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
                seller.increaseCredit(product.getPriceWithOff() * productQuantity);

            }
                seller.addTradeLog(new TradeLog(new Date(),money,moneyWithoutOff - money,sellerProducts.get(seller),buyer.getUserName(),"waiting"));

        }
        if(cart.getCodedDiscount() != null)
            cart.getCodedDiscount().reduceDiscountCodeForUser(buyer);
        buyer.addTradeLog(new TradeLog(new Date(),cart.totalPrice(),cart.totalPrice()-cashToPay,cart.getProducts(),buyer.getUserName(),"waiting"));
        buyer.renewCart();


    }
    public ArrayList<String> getCodedDiscounts(Person person){
        ArrayList<String> codedDiscounts = new ArrayList<>();
        for (CodedDiscount discountCode : Database.getInstance().getAllDiscountCodes()) {
            if(discountCode.hasPerson(person))
                codedDiscounts.add(discountCode.getDiscountCode());
        }
        return codedDiscounts;
    }
    public static void rateTheProduct(Buyer buyer,String productId , Score score) throws Exception {
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
    public void increaseProduct(Buyer buyer,int num , String productId) throws Exception {
        buyer.getCart().increaseProductQuantity(Database.getInstance().getProductById(productId));
    }

    public void decreaseProduct(Buyer buyer,int num , String productId) throws Exception {
        buyer.getCart().decreaseProductQuantity(Database.getInstance().getProductById(productId));
    }
}
