package Client.View.Menus.UserSectionMenus.BuyerAccount;

import Client.Controller.UserSectionController.BuyerAccountController.BuyerController;
import Client.View.Menus.Menu;

public class Purchase extends Menu {

    public Purchase(Menu superMenu) {
        super(superMenu, "purchase");
    }

    @Override
    public void execute() {
        super.execute();
        payment().show();
        payment().execute();
    }

    private Menu receiverInformation() {
        return new Menu(this, "receiver information") {
            @Override
            public void show() {
                System.out.println("ok. now you must enter your information");
            }

            @Override
            public void execute() {

            }
        };
    }
    private Menu payment() {
        return new Menu(this, "payment") {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    private Menu discountCode() {
        return new Menu(this, "discountCode") {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
}
