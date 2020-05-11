package Client.View.Menus.UserSectionMenus.BuyerAccount;

import Client.Controller.UserSectionController.BuyerAccountController.BuyerController;
import Client.Controller.UserSectionController.BuyerAccountController.CartController;
import Client.Models.Person.Buyer;
import Client.Models.Product;
import Client.View.Menus.Menu;
import Client.View.Menus.ProductMenu;

public class ViewCart extends Menu {

    public ViewCart(Menu superMenu) {
        super(superMenu, "view cart");
        addSubMenu(new Purchase(this));

    }

    @Override
    public void show() {
        super.show();
        System.out.println();
        System.out.println("commands : ");
        System.out.println("show Products");
        System.out.println("view [productId]");
        System.out.println("increase [productId]");
        System.out.println("decrease [productId]");
        System.out.println("show total price");
    }

    @Override
    public void execute() {
        super.execute();
        if(command.equalsIgnoreCase("show products")){
            showProducts();
        }
        else if(command.equalsIgnoreCase("show total price")){
            showTotalPrice();
        }
        else if(command.startsWith("view")){
            try {
                viewProduct(command.split(" ")[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else if(command.startsWith("increase")){
            try {
                increase(command.split(" ")[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else if(command.startsWith("decrease")){
            try {
                decrease(command.split(" ")[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("invalid command");
        }
            this.show();
            this.execute();

    }
    private void showProducts(){
        Buyer buyer = (Buyer)BuyerController.getLoggedInPerson();
        for (Product product : BuyerController.getInstance().getCart().getProducts().keySet()) {
            System.out.println(product.getProductId());
        }
        this.show();
        this.execute();
    }
    private void showTotalPrice(){
        int totalPrice = BuyerController.getInstance().getCart().totalPrice();
        System.out.println(totalPrice);
        this.show();
        this.execute();
    }
    private void viewProduct(String productId) throws Exception {
        ProductMenu productMenu = new ProductMenu(this);
        productMenu.setTheProduct(BuyerController.getInstance().getProduct(productId));
        productMenu.show();
        productMenu.execute();
    }
    private void increase(String productId) throws Exception {
        Product product = BuyerController.getInstance().getProduct(productId);
        BuyerController.getInstance().getCart().increaseProductQuantity(product);
        this.show();
        this.execute();
    }
    private void decrease(String productId) throws Exception {
        Product product = BuyerController.getInstance().getProduct(productId);
        BuyerController.getInstance().getCart().decreaseProductQuantity(product);
        this.show();
        this.execute();
    }

}
