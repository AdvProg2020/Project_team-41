package Client.View.Menus.UserSectionMenus.BuyerAccount;

import Client.Controller.UserSectionController.BuyerAccountController.CartController;
import Client.View.Menus.Menu;
import Client.View.Menus.ProductMenu;

public class ViewCart extends Menu {

   // private Product productPage = new Product(this);

    public ViewCart(Menu superMenu) {
        super(superMenu, "view cart");

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        String input = scanner.nextLine();
        if(input.equals("back")){
            this.superMenu.show();
            this.superMenu.execute();
        }
        else if(input.equals("show products")){
            //todo print products in the cart
        }
        else if(input.startsWith("view")){
            //todo initialize productIdThatIsGiven
            String productIdThatIsGiven = "";
            ProductMenu productPage = new ProductMenu(this  , productIdThatIsGiven);
            this.addSubMenu( productPage );
            productPage.show();
            productPage.execute();
        }
        else if(input.startsWith("increase")){
            //TODO initialize num & productId
            int num = -1;
            String productId = "";
            CartController.increaseProduct(num , productId);
        }
        else if(input.startsWith("decrease")){
            //TODO initialize num & productId
            int num = -1;
            String productId = "";
            CartController.decreaseProduct(num , productId);
        }
        else if(input.equals("show total price")){
            System.out.println(CartController.calculateTotalPrice());
        }
        else if(input.equals("purchase")){
            //todo
            Purchase purchasePage = new Purchase(this );
            this.addSubMenu( purchasePage );
            purchasePage.show();
            purchasePage.execute();
        }
    }
}
