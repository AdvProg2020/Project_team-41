package Client.Controller;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Comment;
import Client.Models.CommentSituation;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.Buyer;
import Client.Models.Product;
import Server.Controller.ProductServerController;

public class ProductController {
    private static ProductController single_instance = null;

    public static ProductController getInstance() {
        if (single_instance == null)
            single_instance = new ProductController();

        return single_instance;
    }

    private ProductController() {
    }

    public static void addToCart(Product product) throws ClassCastException , NullPointerException {
        if (UserSectionController.getLoggedInPerson() != null) {
            if(UserSectionController.getLoggedInPerson() instanceof Buyer) {
                Buyer theBuyer = (Buyer) UserSectionController.getLoggedInPerson();
                theBuyer.getCart().addProduct(product);
            }
            else
                throw new ClassCastException("Sorry, only buyers can buy!");

        } else
            throw new NullPointerException("First you must log in");

    }


    public static void addComment(String title, String content, Product product) throws Exception {
        if (UserSectionController.getLoggedInPerson() == null) {
            throw new NullPointerException("You must first login!");
        }

        Comment comment = new Comment(UserSectionController.getLoggedInPerson(), product, title, content, CommentSituation.WAITING , false);
        for (Product tradedProduct : UserSectionController.getLoggedInPerson().getAllProductsHeTraded()) {
            if (product.equals(tradedProduct)) {
                comment.setHasHeBought(true);
            }
        }
        Connector.getInstance().initializeMessage(new Message(new Object[]{comment} , MessageType.ADD_COMMENT));
        //ProductServerController.getInstance().addComment(comment);
    }

    public int amountOfDiscount(String productId) throws Exception {
       // return ProductServerController.getInstance().amountOfDiscount(productId);
        return (int) Connector.getInstance().initializeMessage(new Message(new Object[]{productId} , MessageType.AMOUNT_OF_DISCOUNT));
    }
    }

