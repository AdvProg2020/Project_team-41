package Server.Controller;

import Client.Models.*;
import Client.Models.Person.Buyer;
import Client.Models.Person.Person;
import Server.Database;

import javax.xml.crypto.Data;
import java.util.Date;

public class ProductServerController {
    private static ProductServerController single_instance = null;
    public static ProductServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new ProductServerController();

        return single_instance;
    }
    private ProductServerController(){
    }

    public int amountOfDiscount(String productId) throws Exception {
        Date date=new Date();
        for (Off off : Database.getInstance().getAllOffs()) {
            for (Product product : off.getProducts()) {
                if(product.getProductId().equals(productId)){
                    if(date.before(off.getStartDate())){
                        throw new Exception("There is no discount for this product at this time.");
                    }
                    return off.getAmountOfDiscount();
                }
            }
        }
        throw new Exception("There is no discount for this product at this time.");
    }
    public void addComment(String username , String productId , String title , String content) throws Exception {
        Person person = Database.getInstance().getPersonByUsername(username);
        Product product = Database.getInstance().getProductById(productId);
        Comment comment = new Comment(person, product, title, content, CommentSituation.WAITING , false);
        for (Product tradedProduct : person.getAllProductsHeTraded()) {
            if (product.equals(tradedProduct)) {
                comment.setHasHeBought(true);
            }
        }
        Request request=new Request(comment);
        Database.getInstance().addRequest(request);
    }

    public void addToCart(Buyer buyer, Product product) throws Exception {
        buyer = (Buyer) Database.getInstance().getPersonByUsername(buyer.getUserName());
        buyer.getCart().getProducts().put(Database.getInstance().getProductById(product.getProductId()) , 1);

    }

    public void increaseProductView(String productId) throws Exception {
        Product product = Database.getInstance().getProductById(productId);
        product.setViews(product.getViews() + 1);
    }

    public void addScore(String username, int scoreNumber, String productId) throws Exception {
        Product product = Database.getInstance().getProductById(productId);
        Score score = new Score(Database.getInstance().getPersonByUsername(username) , scoreNumber , product);
        product.addScore(score);
    }
}