package Client.Controller;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Comment;
import Client.Models.CommentSituation;
import Client.Models.Person.Buyer;
import Client.Models.Product;
import Client.Models.Request;
import Client.View.Menus.UserSectionMenus.UserSection;
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

    public static void addToCart(Product product) throws Exception {

        if (UserSectionController.getLoggedInPerson() != null) {
            Buyer theBuyer = (Buyer) UserSectionController.getLoggedInPerson();
            theBuyer.getCart().getProducts().put(product, 1);
        } else
            throw new Exception("First you must log in");

    }

    public static void addComment(String title, String content, Product product) throws Exception {

        if (UserSectionController.getLoggedInPerson() == null) {
            throw new Exception("You must first login!");
        }

        Comment comment = new Comment(UserSectionController.getLoggedInPerson(), product, title, content, CommentSituation.WAITING);
        comment.setHasHeBought(false);
        for (Product tradedProduct : UserSectionController.getLoggedInPerson().getAllProductsHeTraded()) {
            if (product.equals(tradedProduct)) {
                comment.setHasHeBought(true);
            }
        }
        ProductServerController.getInstance().addComment(comment);


    }
    public int amountOfDiscount(String productId) throws Exception {
        return ProductServerController.getInstance().amountOfDiscount(productId);
    }
}
