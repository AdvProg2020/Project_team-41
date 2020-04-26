package Client.View.Menus.UserSectionMenus.BuyerAccount;

import Client.Controller.UserSectionController.BuyerAccountController.BuyerController;
import Client.View.Menus.Menu;
import Client.View.Menus.UserSectionMenus.UserSection;

public class BuyerSection extends UserSection {
    public BuyerSection(Menu superMenu) {
        super(superMenu, "BuyerSection");
        this.addSubMenu(new ViewCart(this));
        this.addSubMenu(new Purchase(this));
        this.addSubMenu(viewOrders());
        this.addSubMenu(viewBalance());
        this.addSubMenu(viewDiscountCodes());
    }

    private Menu viewOrders() {
        return new Menu(this, "view order") {
            @Override
            public void show() {
                //TODO show shopping history
            }

            @Override
            public void execute() {
                String input;
                while (!(input = scanner.nextLine()).equals("back")) {
                    if (input.startsWith("show order")) {
                        String orderId = "";
                        BuyerController.showTheOrder(orderId);
                        //TODO print the details
                    } else if (input.startsWith("rate")) {
                        //TODO get productID & score
                        String productId = "";
                        int score = -1;
                        BuyerController.rateTheProduct(productId, score);
                        //TODO print the result
                    }
                    this.superMenu.show();
                    this.superMenu.execute();
                }
            }
        };
    }

    private Menu viewBalance(){
        return new Menu(this , "view balance") {
            @Override
            public void show() {
                //TODO print the balance
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

    private Menu viewDiscountCodes(){
        return new Menu(this , "view discount codes") {
            @Override
            public void show() {
                //TODO print discount codes
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }
}