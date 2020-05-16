package Server.Controller.UserSectionController;

import Client.Models.*;
import Client.Models.Person.Buyer;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Server.Database;
import com.ibm.icu.text.ArabicShaping;

import java.io.Serializable;
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
        HashMap<Seller,HashMap<Product,Integer>> sellerProducts = new HashMap<>();
        if(buyer.getCredit()<cart.totalPrice())
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
        }
        buyer.decreaseCredit(cart.totalPrice());
        for (Seller seller : sellerProducts.keySet()) {
            int money = 0;
            for (Product product : sellerProducts.get(seller).keySet()) {
                int productQuantity = sellerProducts.get(seller).get(product);
                money += product.getPrice() * productQuantity;
                product.decreaseQuantity(productQuantity);
                seller.addCredit(product.getPrice() * productQuantity);

            }
                seller.addTradeLog(new TradeLog(new Date(),money,0,sellerProducts.get(seller),buyer.getUserName(),"waiting"));
        }
        buyer.addTradeLog(new TradeLog(new Date(),cart.totalPrice(),0,cart.getProducts(),buyer.getUserName(),"waiting"));

        //todo check offAmount and deliverySituation

    }
    public ArrayList<String> getCodedDiscounts(Person person){
        ArrayList<String> codedDiscounts = new ArrayList<>();
        for (CodedDiscount discountCode : Database.getAllDiscountCodes()) {
            if(discountCode.hasPerson(person))
                codedDiscounts.add(discountCode.getDiscountCode());
        }
        return codedDiscounts;
    }
    public static void rateTheProduct(String productId , Score score) throws Exception {
        Database.getProductById(productId).addScore(score);
    }
    public Product getProduct(String productId) throws Exception {
        return Database.getProductById(productId);
    }
    public void addCodedDiscountToCart(Buyer buyer,String discountCode) throws Exception {
        CodedDiscount codedDiscount = Database.getCodedDiscountByCode(discountCode);
        if(codedDiscount.hasPerson(buyer)) {
            buyer.getCart().setCodedDiscount(codedDiscount);
        }
        else {
            throw new Exception("you don't have this discount code");
        }
    }
    public void increaseProduct(Buyer buyer,int num , String productId) throws Exception {
        buyer.getCart().increaseProductQuantity(Database.getProductById(productId));
    }

    public void decreaseProduct(Buyer buyer,int num , String productId) throws Exception {
        buyer.getCart().decreaseProductQuantity(Database.getProductById(productId));
    }
}
