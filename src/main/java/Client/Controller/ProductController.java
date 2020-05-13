package Client.Controller;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Comment;
import Client.Models.CommentSituation;
import Client.Models.Person.Buyer;
import Client.Models.Product;
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

    public static void addComment(String title, String content, Product product) {
        Comment comment = new Comment(UserSectionController.getLoggedInPerson(), product, title, content, CommentSituation.WAITING);
        product.getComments().add(comment);


        if (UserSectionController.getLoggedInPerson() == null) {
            System.out.println("First please Login");
        } else {
            for (Product tradedProduct : UserSectionController.getLoggedInPerson().getAllProductsHeTraded()) {
                if (product.equals(tradedProduct)) {
                    comment.setHasHeBought(true);
                }
            }
            comment.setHasHeBought(false);
        }
    }
    public int amountOfDiscount(String productId) throws Exception {
        return ProductServerController.getInstance().amountOfDiscount(productId);
    }
}
