package Server.Controller.UserSectionController;

import Client.Models.*;
import Client.Models.Person.Buyer;
import Client.Models.Person.Person;
import Server.Database;
import com.ibm.icu.text.ArabicShaping;

import java.util.ArrayList;

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
        for (Product product : cart.getProducts().keySet()) {
            product.decreaseQuantity();
            product.getSeller().addCredit(product.getPrice());
        }
        buyer.decreaseCredit(cart.totalPrice());

        //todo make tradelogs
        //TODO paying process
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
